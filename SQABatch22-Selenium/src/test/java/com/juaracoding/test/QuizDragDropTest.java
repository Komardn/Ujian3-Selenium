package com.juaracoding.test;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class QuizDragDropTest {
  WebDriver driver;

  @BeforeClass
  @Parameters({ "url" })
  public void init(String url) {
    driver = DriverSingleton.createOrGetDriver();
    driver.get(url);
  }

  @Test(enabled = false)
  public void testStep01() throws InterruptedException {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("window.scrollBy({top: 300, behavior: 'smooth'})");
    Thread.sleep(2000);

    WebElement draggable = driver.findElement(By.xpath("//div[@id='box1']"));
    WebElement drop = driver.findElement(By.id("box101"));
    Actions builder = new Actions(driver);

    Action dragger = builder.clickAndHold(draggable)
        .pause(Duration.ofSeconds(2))
        .moveToElement(drop, 0, 2)
        .pause(Duration.ofSeconds(2))
        .release()
        .pause(Duration.ofSeconds(3))
        .build();
    dragger.perform();

    Thread.sleep(1000);
    String bgColor = draggable.getCssValue("background-color");
    String expected = "rgba(0, 255, 0, 1)";
    Thread.sleep(5000);
    Assert.assertEquals(bgColor, expected);
  }

  private void dragAndDrop(String idDrag, String idDrop) {
    WebElement draggable = driver.findElement(By.id(idDrag));
    WebElement drop = driver.findElement(By.id(idDrop));

    Actions builder = new Actions(driver);
    Action dragger = builder.clickAndHold(draggable)
        .pause(Duration.ofSeconds(2))
        .moveToElement(drop, 0, 2)
        .pause(Duration.ofSeconds(2))
        .release()
        .pause(Duration.ofSeconds(3))
        .build();
    dragger.perform();
  }

  private void draggableSantui(String idDrag, String idDrop) {
    WebElement draggable = driver.findElement(By.id(idDrag));
    WebElement drop = driver.findElement(By.id(idDrop));
    Actions builder = new Actions(driver);
    builder.dragAndDrop(draggable, drop).perform();
  }

  private void customDragableSantui(String idDrag, String idDrop) {
  }

  @Test
  public void mingguO2Test() throws InterruptedException {
    Map<String, String> map = new LinkedHashMap<>();
    map.put("box1", "box101");
    map.put("box2", "box102");
    map.put("box3", "box103");
    map.put("box4", "box104");
    map.put("box5", "box105");
    map.put("box6", "box106");
    map.put("box7", "box107");

    for (Map.Entry<String, String> entry : map.entrySet()) {
      draggableSantui(entry.getKey(), entry.getValue());
      Thread.sleep(1000);
    }

    for (String box : map.keySet()) {
      draggableSantui(box, "capitals");
      Thread.sleep(1000);
    }
  }
}