//package com.MelodyMateBackend.service;
//
//import com.MelodyMateBackend.domain.Song;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class CrawlingService {
//
//    private SongMapper dao;
//    private CrawlingUrl dataUrl;
//
//    @Autowired
//    public CrawlingService(SongMapper dao, CrawlingUrl dataUrl) {
//        this.dao = dao;
//        this.dataUrl = dataUrl;
//    }
//
//    @Scheduled(fixedDelay = 3600000)
//    public void dataCrawling() throws InterruptedException {
//        // WebDriver 경로 설정 (###경로 변경 필요)
//        System.setProperty("webdriver.chrome.driver", "D:\\backup\\Development\\2. Toy\\chromedriver\\chromedriver.exe");
//
//        // WebDriver 옵션 설정, 객체 생성
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
////        options.addArguments("headless");     // 크롬창 비활성화 진행
//        WebDriver driver = new ChromeDriver(options);
//
//        // 웹페이지 요청
//        String url = dataUrl.getUrl();
//        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get(url);
//
//        crawling(driver, webDriverWait);
//
//        WebElement pageButton = driver.findElement(By.cssSelector("#paging > a:nth-child(2)"));
//        pageButton.click();
//        Thread.sleep(200);
//
//        crawling(driver, webDriverWait);
//
//        // TODO: 기존 데이터 삭제 추가 예정
//
//
//    }
//
//    private void crawling(WebDriver driver, WebDriverWait webDriverWait) throws InterruptedException {
//        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#list > li")));
//        List<WebElement> firstElementList = driver.findElements(By.cssSelector("#list > li"));
//
//        // 1위 ~ 50위
//        for (WebElement element : firstElementList) {
//            Song song = new Song();
//
//            String[] rankArr = element.findElement(By.cssSelector("div.board_item > div.ranking")).getText().split("\\n");
//            String[] artistTitle = element.findElement(By.cssSelector("div.board_item > div.title > div")).getText().split("\\n");
//            String viewCountString = element.findElement(By.cssSelector("div.board_item > div.views")).getText();
//            int rank = Integer.parseInt(rankArr[0]);
//            String songTitle = artistTitle[1];
//            String artist = artistTitle[0];
//            String releaseDate = element.findElement(By.cssSelector("div.board_item > div.release")).getText();
//            String thumbnail = element.findElement(By.cssSelector("div.board_item > div.title > a > figure > img:nth-child(4)")).getAttribute("src");
//            String url = element.findElement(By.cssSelector("div.board_item > div.title > a")).getDomAttribute("data-url");
//
//            // youtube 재생 링크에서 재생시간 가져오기
//            WebElement ytbButton = element.findElement(By.cssSelector("div.board_item > div.title > a"));
//            ytbButton.click();
//
//            // iframe 전환
//            driver.switchTo().frame(driver.findElement(By.cssSelector("#youtubeVideo > iframe")));
//            Thread.sleep(300);
//
//            // 유튜브 재생 버튼 클릭
//            WebElement startButton = driver.findElement(By.cssSelector("#movie_player > div.ytp-cued-thumbnail-overlay > button"));
//            startButton.click();
//            Thread.sleep(300);
//
//            // 저장 데이터 선언
//            String duration = driver.findElement(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > div.ytp-time-display.notranslate > span:nth-child(2) > span.ytp-time-duration")).getText();
//            String month = String.valueOf(LocalDate.now().getMonth());
//
//            // setter 삽입
//            song.setSongTitle(songTitle);
//            song.setArtist(artist);
//            song.setReleaseDate(releaseDate);
//            song.setViewCount(viewCountString);
//            song.setThumbnail(thumbnail);
//            song.setUrl(url);
//            song.setDuration(duration);
//            song.setRank(rank);
//            song.setChartMonth(month);
//
//            // db 저장
//            dao.chartSave(song);
//
//            // 원래 소스 전환
//            driver.switchTo().defaultContent();
//
//            // 닫기 버튼 클릭
//            WebElement closeButton = driver.findElement(By.cssSelector("#youtubeModal > a"));
//            closeButton.click();
//            Thread.sleep(300);
//
//        }
//    }
//}
