/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Pedro
 */
public class SeleniumTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pedro\\Downloads\\chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "http://localhost:8383/TQS_Interface/index.html";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Test
  public void testSelect() throws InterruptedException {
      driver.get(baseUrl);
      
      Select dropdown_1 = new Select(driver.findElement(By.id("select_one")));
      dropdown_1.selectByValue("USD");
      Select dropdown_2 = new Select(driver.findElement(By.id("select_two")));
      dropdown_2.selectByValue("EUR");

      
      driver.findElement(By.id("amount")).clear();
      driver.findElement(By.id("amount")).sendKeys("10");
      driver.findElement(By.id("amount")).sendKeys(Keys.ENTER);
      DecimalFormat df = new DecimalFormat("#.#");
      df.setRoundingMode(RoundingMode.CEILING);
      double result = 8.25504;
      Thread.sleep(3000);
      String[] text = driver.findElement(By.id("result")).getText().split(" ");
      System.out.println(driver.findElement(By.id("result")).getText());
      
      assertEquals(df.format(Double.parseDouble(text[text.length-1])),df.format(result));
  }
  
  @Test
  public void testCantFindDataAlert(){
      driver.get(baseUrl);
      Select dropdown_1 = new Select(driver.findElement(By.id("select_one")));
      dropdown_1.selectByValue("AZN");
      assertTrue(checkAlert());
  }
  
  
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
  public boolean checkAlert() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return true;
    } catch (Exception e) {
        //exception handling
        
    }
    return false;
}
    
  
}


