package com.juaracoding.test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ScreenshotHelper {
  public static void takeScreenshot(String fileName, WebDriver driver) {
    try {

      String screenshotDir = System.getProperty("user.dir") + "/screenshots";
      Files.createDirectories(Paths.get(screenshotDir));

      File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      String filePath = screenshotDir + "/" + fileName;
      File destFile = new File(filePath);

      FileUtils.copyFile(srcFile, destFile);

      Reporter.log("<a href='" + filePath + "' target='_blank'>Screnshoot</a>");
      Reporter.log("<br /><img src='" + filePath + "' style='width: 20vw;' />");
    } catch (Exception e) {

      Reporter.log("Gagal ngambil screenshot: " + e.getMessage());
    }
  }
}