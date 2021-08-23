package com.liveinc.th;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Login extends TimerTask {
    public void runTest() {  }

    @Override
    public void run() {
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
            if (!btn.isDisplayed()) {
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
            txtBox.sendKeys("kalebw89@gmail.com");
            btn = driver.findElement(By.className("btn"));
            btn.click();

            Boolean exists;
            try {
                ele = driver.findElement(By.className("form__error"));
                exists = true;
            } catch (Exception e) {
                exists = false;
            }
            if (!exists) {
                txtBox = driver.findElement(By.id("password"));
                txtBox.sendKeys("Pa$$w0rd!");
                // WebElement loginForm = driver.findElement(By.tagName("form"));
                // loginForm.submit();
                List<WebElement> btns = driver.findElements(By.className("btn"));
                if (btns.size() == 0) {
                    driver.quit();
                    return;
                }
                // System.out.println(btns.size());
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
                // driver.findElements(By.className("sidebar__list-item")).get(1).click();
                // try {
                // Thread.sleep(4000);
                // } catch (Exception e) {
                // }
                // driver.findElement(By.className("add_job_floating__button")).click();
            } else {
                try {
                    driver.quit();
                    URL url = new URL("https://secure2.managerspecial.com/api/v1/send/message");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    con.setRequestProperty("X-Server-API-Key", "MxodYUHQ5FEImlwY5qW75Br8");

                    JSONArray array = new JSONArray();
                    JSONObject obj = new JSONObject();

                    array.put("kaleb.wond@gmail.com");
                    obj.put("to", array);
                    obj.put("from", "support@secure.trabahanap.com");
                    obj.put("subject", "Check Trabahanap Status");
                    obj.put("html_body", "<div><h2 style='color: red'>Check if Trabahanap is down.</h2><p>"+new Date() +"</p></div>");
                    
                    OutputStream os = con.getOutputStream();
                    os.write(obj.toString().getBytes("UTF-8"));
                    os.close();

                    try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"))) {
                            StringBuilder response = new StringBuilder();
                            String responseLine = null;
                            while ((responseLine = br.readLine()) != null) {
                                response.append(responseLine.trim());
                            }
                            System.out.println(response.toString() + "Hello");
                        }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            driver.quit();
            
        }
        
    }
}
