package com.project.MelodyMateBackend.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class CrawlingService {
    public void dataCrawling() {
        System.setProperty("webdriver.chrome.driver", "D:\\backup\\Development\\2. Toy\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        String url = "https://www.kpop-radar.com/?type=1&date=4&gender=1#";

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(url);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#list > li:nth-child(1) > div.board_item")));

        List<WebElement> elementList = driver.findElements(By.cssSelector("#list > li:nth-child(1) > div.board_item > div.title > a"));

        System.out.println("실행");
        for (WebElement element : elementList) {
            System.out.println("-----------------");
            System.out.println(element.getCssValue("data-url"));
        }
    }
}
