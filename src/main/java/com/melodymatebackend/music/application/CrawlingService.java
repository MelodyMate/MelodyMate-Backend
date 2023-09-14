package com.melodymatebackend.music.application;

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
import org.openqa.selenium.chrome.ChromeOptions;
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

    @Transactional(readOnly = false)
    @Scheduled(cron = "0 5 0 * * ?")
//    @Scheduled(fixedDelay = 360000)
    public void crawlingMain() throws InterruptedException {
        WebDriver driver = getWebDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver/chromedriver");
        dataCrawling(driver, js);
        WebElement pageButton = driver.findElement(By.cssSelector("#paging > a:nth-child(2)"));
        pageButton.click();

        dataCrawling(driver, js);

        log.info("끝");
        driver.quit();

    }

    private static WebDriver getWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("window-size=1920x1080");
        options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Whale/3.21.192.22 Safari/537.36");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.kpop-radar.com/?type=1&date=2&gender=1");
        return driver;
    }


    @Transactional(readOnly = false)
    public void dataCrawling(WebDriver driver, JavascriptExecutor js) {
        try {
            WebDriverWait mainWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            mainWait.until(ExpectedConditions.presenceOfElementLocated(By.className("board_item")));

            // 추출
            List<WebElement> boardItems = driver.findElements(By.className("board_item"));

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

                    // 재생시간 추출
//                    durationDriver.get(dataUrl);
//                    WebElement durationElement = durationDriver.findElement(By.cssSelector("span.ytp-time-duration"));
//                    String duration = durationElement.getText();

                    log.info("재생시간 추출");

                    WebElement ytbButton = boardItem.findElement(By.cssSelector("div.board_item > div.title > a"));
                    ytbButton.click();

                    // iframe 전환
                    driver.switchTo().frame(driver.findElement(By.cssSelector("#youtubeVideo > iframe")));
                    Thread.sleep(300);

                    // 유튜브 재생 버튼 클릭
                    WebElement startButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-cued-thumbnail-overlay > button"));
                    startButton.click();
                    Thread.sleep(300);

                    // 저장 데이터 선언
                    String duration = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > div.ytp-time-display.notranslate > span:nth-child(2) > span.ytp-time-duration")).getText();

                    musicDTO.setRanking(Integer.parseInt(ranking));
                    musicDTO.setUrl(dataUrl);
                    musicDTO.setMusicTitle(title);
                    musicDTO.setArtist(artist);
                    musicDTO.setThumbnail(thumbnail);
                    musicDTO.setDuration(duration);
//                    musicDTO.setDuration("00:00");
                    musicDTO.setViewCount(views);
                    musicDTO.setReleaseDate(releaseDate);
                    musicDTO.setRankDate(LocalDate.now());

                    Music music = musicDTO.toEntity();

                    log.info(ranking + " / " + artist + " / " + title + " / " + views + " / " + releaseDate);

                    driver.switchTo().defaultContent();
                    WebElement closeButton = driver.findElement(By.cssSelector("#youtubeModal > a"));
                    closeButton.click();
                    Thread.sleep(300);


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
