package com.juaracoding.test.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.juaracoding.test.DriverSingleton;
import com.juaracoding.test.ScreenshotHelper;

public class HookSS implements IInvokedMethodListener {
  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    log("Commencing", method);
    ScreenshotHelper.takeScreenshot(method.getTestMethod().getMethodName() + "_before.png",
        DriverSingleton.createOrGetDriver());
  }

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    log("Completed", method);

    ScreenshotHelper.takeScreenshot(method.getTestMethod().getMethodName() + "_after.png",
        DriverSingleton.createOrGetDriver());
  }

  private static void log(String prefix, IInvokedMethod method) {
    String type = "Configuration";
    if (method.isTestMethod()) {
      type = "Test";
    }
    String msg = prefix + " executing [" + type + "] method "
        + method.getTestMethod().getQualifiedName();
    System.err.println(msg);
  }

}