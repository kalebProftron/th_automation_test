package com.liveinc.th;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Login {
    public void runTest() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver");
        Map<String, Object> prefs = new HashMap<String, Object>();
        // response to notification popup        
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        try {
            WebElement btn;
            WebElement txtBox;
            WebElement ele;
            WebElement ele2;

            // make brower window maximized
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://trabahanap.com");
            
            btn = driver.findElement(By.className("sign-in"));
            if(!btn.isDisplayed()) {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
            }
            btn.click();
            System.out.println("Log in page...");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            txtBox = driver.findElement(By.id("email"));
            txtBox.sendKeys("silogecho@gmail.com");
            btn = driver.findElement(By.className("btn"));
            btn.click();
            
            Boolean exists;
            try {
                ele = driver.findElement(By.className("form__error"));
                exists = true;
            } catch (Exception e) {
                exists = false;
            }
            if(!exists) {
                txtBox = driver.findElement(By.id("password"));
                txtBox.sendKeys("password");
                // WebElement loginForm = driver.findElement(By.tagName("form"));
                // loginForm.submit();
                List<WebElement> btns = driver.findElements(By.className("btn"));
                if(btns.size() == 0) {
                    driver.quit();
                    return;
                }
                System.out.println(btns.size());
                btn = btns.get(1);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                btn.click();

                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                }
                driver.quit();         
            }
        } catch (Exception e) {
            System.out.println(e);
            driver.quit();
        }
    }
}
