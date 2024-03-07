package com.melodymatebackend.music.application;

import com.google.common.collect.ImmutableMap;
import com.melodymatebackend.music.application.dto.MusicDTO;
import com.melodymatebackend.music.application.dto.RankingDTO;
import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.MusicRepository;
import com.melodymatebackend.music.domain.Ranking;
import com.melodymatebackend.music.domain.RankingsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
@Service
public class CrawlingService {

    private final MusicRepository musicRepository;
    private final RankingsRepository rankingsRepository;

    private final MusicService musicService;


    @Transactional(readOnly = false)
    @Scheduled(cron = "0 5 0 * * ?")
//    @Scheduled(fixedDelay = 360000)
    public void crawlingMain() throws IOException, InterruptedException {

        // rankings 테이블에서 크롤링하는 날짜 지우기
        LocalDate today = LocalDate.now();
        rankingsRepository.deleteRankingByRankDate(today);

        String url = "https://www.kpop-radar.com/?type=1&date=2&gender=1";

        WebDriver driver = getWebDriver(url);
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        dataCrawling(driver, js);
        // 2페이지 이동
        WebElement pageButton = driver.findElement(By.cssSelector("#paging > a:nth-child(2)"));
        pageButton.click();

        dataCrawling(driver, js);

        log.info("종료");
        js.executeScript("window.close()");
        driver.quit();
        log.info("끝");

    }

    private static WebDriver getWebDriver(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("window-size=1920x1080");
        options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Whale/3.21.192.22 Safari/537.36");

        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        return driver;
    }

    @Transactional(readOnly = false)
    public void dataCrawling(WebDriver driver, JavascriptExecutor js) throws InterruptedException {
        int maxRetry = 3;

        WebDriverWait mainWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainWait.until(ExpectedConditions.presenceOfElementLocated(By.className("board_item")));
        // 추출
        List<WebElement> boardItems = driver.findElements(By.className("board_item"));

        for (WebElement boardItem : boardItems) {
            int retryCount = 0;
            while (retryCount < maxRetry) {
                log.info("count = " + retryCount);
                try {
                    MusicDTO musicDTO = new MusicDTO();
                    RankingDTO rankingDTO = new RankingDTO();

                    // url 추출
                    WebElement urlElement = boardItem.findElement(By.cssSelector("a[data-url]"));
                    String dataUrl = urlElement.getAttribute("data-url");

                    // 순위 추출
                    WebElement rankingElement = boardItem.findElement(By.cssSelector(".ranking"));
                    String ranking = (String) js.executeScript("return arguments[0].firstChild.textContent.trim()", rankingElement);

                    // 가수 추출(태그가 다를 경우 오류 발생)
                    String artist;
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

                    // 재생시간 추출
//                    Thread.sleep(300);
//                    WebElement ytbButton = boardItem.findElement(By.cssSelector("div.board_item > div.title > a"));
//                    ytbButton.click();
//
//                    // iframe 전환
//                    driver.switchTo().frame(driver.findElement(By.cssSelector("#youtubeVideo > iframe")));
//                    Thread.sleep(1000);
//
//                    // 유튜브 재생 버튼 클릭
//                    WebElement startButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-cued-thumbnail-overlay > button"));
//                    startButton.click();
//                    Thread.sleep(500);
//
//                    WebElement durationElement = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > div.ytp-time-display.notranslate > span:nth-child(2) > span.ytp-time-duration"));
//                    String duration = durationElement.getText();

                    musicDTO.setUrl(dataUrl);
                    musicDTO.setArtist(artist);
                    musicDTO.setTitle(title);
                    musicDTO.setThumbnail(thumbnail);
                    musicDTO.setDuration("00:00");
                    musicDTO.setViewCount(views);
                    musicDTO.setReleaseDate(releaseDate);

                    if (!musicDTO.isValid()) {
                        retryCount++;
                        log.error("data is null");
                        return;
                    }

                    Music music = musicDTO.toEntity();

                    musicRepository.save(music);

                    rankingDTO.setRank(ranking);
                    rankingDTO.setMusic(music);
                    rankingDTO.setRankDate(LocalDate.now());

                    Ranking rankingDTOEntity = rankingDTO.toEntity();
                    rankingsRepository.save(rankingDTOEntity);

                    break;


                } catch (Exception e) {
                    retryCount++;
                    log.error(e.getMessage());
                    Thread.sleep(1000);
                }
            }
        }

    }
}
