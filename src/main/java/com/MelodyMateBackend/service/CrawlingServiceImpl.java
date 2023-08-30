package com.MelodyMateBackend.service;

import com.MelodyMateBackend.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

//@RequiredArgsConstructor
//@Transactional
@Slf4j
//@Service
public class CrawlingServiceImpl {
    public static void main(String[] args) {

//        private final SongRepository songRepository;

//    @Scheduled(fixedDelay = 3600000)
//    public void dataCrawling() {

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        WebDriver driver = new ChromeDriver();
        try {
            // 웹사이트 접속
            driver.get("https://www.kpop-radar.com/?type=1&date=2&gender=1");

            List<WebElement> boardItems = driver.findElements(By.className("board_item"));

            for (WebElement boardItem : boardItems) {
                WebElement urlElement = boardItem.findElement(By.cssSelector("a[data-url]"));
                String dataUrl = urlElement.getAttribute("data-url");

                // 순위 추출
                WebElement raankingElement = boardItem.findElement(By.cssSelector(".ranking"));
                String ranking = raankingElement.getText();

                // 제목을 추출합니다.
                WebElement titleElement = boardItem.findElement(By.cssSelector(".title strong"));
                String title = titleElement.getText();

                // 조회수를 추출합니다. (클래스 이름은 예시입니다. 실제 코드에서 확인해 주세요.)
                WebElement viewsElement = boardItem.findElement(By.cssSelector(".views span"));
                String views = viewsElement.getText();

                // 릴리즈 날짜를 추출합니다. (클래스 이름은 예시입니다. 실제 코드에서 확인해 주세요.)
                WebElement releaseElement = boardItem.findElement(By.cssSelector(".release span"));
                String releaseDate = releaseElement.getText();

                System.out.println("Ranking: " + ranking);
                System.out.println("Data URL: " + dataUrl);
                System.out.println("Title: " + title);
                System.out.println("Views: " + views);
                System.out.println("Release Date: " + releaseDate);
                System.out.println("----------------------");
            }


        } catch (Exception e) {
            log.error("error occurred", e);
        } finally {
            driver.quit();
        }

    }
}
