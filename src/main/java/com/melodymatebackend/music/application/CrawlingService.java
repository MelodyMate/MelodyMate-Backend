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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
@Service
public class CrawlingService {
    private final MusicRepository musicRepository;
    private final WebDriver driver = new ChromeDriver();
    private final WebDriver durationDriver = new ChromeDriver();
    private final WebDriverWait wait = new WebDriverWait(durationDriver, Duration.ofSeconds(15));


    @Scheduled(fixedDelay = 86400000)
    public void crawlingMain() throws InterruptedException {
        driver.get("https://www.kpop-radar.com/?type=1&date=2&gender=1");
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        dataCrawling();

        WebElement pageButton = driver.findElement(By.cssSelector("#paging > a:nth-child(2)"));
        pageButton.click();

        dataCrawling();

        log.info("끝");

    }

    @Transactional(readOnly = false)
    public void dataCrawling() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            WebDriverWait mainWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            mainWait.until(ExpectedConditions.presenceOfElementLocated(By.className("board_item")));

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

                    // 가수 추출(태그가 다를 경우 오류 발생)
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

                    // 썸네일 추출
                    WebElement thumbnailElement = boardItem.findElement(By.cssSelector(".board_item .cf4a img:nth-of-type(4)"));
                    String thumbnail = thumbnailElement.getAttribute("src");

                    log.info("재생시간 추출");

                    // 재생시간 추출
                    durationDriver.get(dataUrl);
                    WebElement durationElement = durationDriver.findElement(By.cssSelector("span.ytp-time-duration"));
                    String duration = durationElement.getText();


                    musicDTO.setRank(ranking);
                    musicDTO.setUrl(dataUrl);
                    musicDTO.setMusicTitle(title);
                    musicDTO.setArtist(artist);
                    musicDTO.setThumbnail(thumbnail);
                    musicDTO.setDuration(duration);
                    musicDTO.setViewCount(views);
                    musicDTO.setReleaseDate(releaseDate);
                    musicDTO.setRankDate(LocalDate.now());

                    Music music = musicDTO.toEntity();

                    log.info(ranking + " " + artist + " " + title);
                    musicRepository.save(music);

                } catch (Exception e) {
                    log.error(e.getMessage());
                    continue;
                }
            }


        } catch (Exception e) {
            log.error("error occurred", e);
        }

    }
}
