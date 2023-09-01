package com.melodymatebackend.music.application;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.melodymatebackend.music.application.dto.MusicDTO;
import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
@Service
public class CrawlingService {
    private final MusicRepository musicRepository;

    @Transactional(readOnly = false)
    @Scheduled(fixedDelay = 3600000)
    public void dataCrawling() {

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            // 접속
            driver.get("https://www.kpop-radar.com/?type=1&date=2&gender=1");

            // 추출
            List<WebElement> boardItems = driver.findElements(By.className("board_item"));

            // 1-50위 저장
            for (WebElement boardItem : boardItems) {
                try {
                    MusicDTO musicDTO = new MusicDTO();

                    // url 추출
                    WebElement urlElement = boardItem.findElement(By.cssSelector("a[data-url]"));
                    String dataUrl = urlElement.getAttribute("data-url");

                    // 순위 추출
                    WebElement raankingElement = boardItem.findElement(By.cssSelector(".ranking"));
                    String ranking = (String) js.executeScript("return arguments[0].firstChild.textContent.trim()", raankingElement);

                    // 가수 추출
                    String artist = "";
                    try {
                        WebElement artistElement = boardItem.findElement(By.cssSelector(".title a span"));
                        artist = artistElement.getText();
                    } catch (Exception e) {
                        WebElement noArtistElement = boardItem.findElement(By.cssSelector(".title .no_artist_board"));
                        artist = noArtistElement.getText();
                    }

                    // 제목 추출
                    WebElement titleElement = boardItem.findElement(By.cssSelector(".title strong"));
                    String title = titleElement.getText();

                    // 조회수 추출
                    WebElement viewsElement = boardItem.findElement(By.cssSelector(".views span"));
                    String views = viewsElement.getText();

                    // 릴리즈 날짜 추출
                    WebElement releaseElement = boardItem.findElement(By.cssSelector(".release span"));
                    String releaseDate = releaseElement.getText();
                    System.out.println(ranking + " " + artist);

                    // 썸네일 추출
                    WebElement thumbnailElement = boardItem.findElement(By.cssSelector(".board_item .cf4a img:nth-of-type(4)"));
                    String thumbnail = thumbnailElement.getAttribute("src");

                    musicDTO.setRank(ranking);
                    musicDTO.setUrl(dataUrl);
                    musicDTO.setMusicTitle(title);
                    musicDTO.setArtist(artist);
                    musicDTO.setThumbnail(thumbnail);

                    /// TODO : duration 개발
                    musicDTO.setDuration("03:03");
                    musicDTO.setViewCount(views);
                    musicDTO.setReleaseDate(releaseDate);
                    musicDTO.setRankDate(LocalDate.now());

                    Music music = musicDTO.toEntity();

                    musicRepository.save(music);

                } catch (Exception e) {
                    continue;
                }
            }


        } catch (Exception e) {
            log.error("error occurred", e);
        } finally {
            driver.quit();
        }

    }
}
