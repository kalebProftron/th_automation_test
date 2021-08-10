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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterApplicant {
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
            WebElement ele;
            WebElement ele2;

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
            
            List<WebElement> links = driver.findElements(By.xpath("//span//a"));
            links.get(0).click();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            WebElement submit = driver.findElement(By.xpath("//auth-custom-submit//button"));
            List<WebElement> inputs = driver.findElements(By.tagName("input"));
            inputs.remove(6);
            String[] vals = {"John", "Doe", "junk@junk.com", "+1 (578) 387-8878", "password", "password"};
            for (int i = 0; i < inputs.size(); i++) {
                inputs.get(i).sendKeys(vals[i]);
            }
            ele = driver.findElement(By.className("checkbox__button"));
            ele.click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
            
            new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();

            try {
                Thread.sleep(25000);
            } catch (Exception e) {
            }
            submit.click();
        } catch (Exception e) {
            System.out.println(e);
            driver.quit();
        }
    }
}
