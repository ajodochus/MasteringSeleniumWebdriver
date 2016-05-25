package com.masteringselenium.tests;

import com.masteringselenium.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class JavaScriptExamplesWD extends DriverFactory {

    @Test
    private void javascriptExample1() throws Exception {
        FirefoxDriver driver = new FirefoxDriver(new FirefoxProfile());
        driver.executeScript("console.log('I logged something to the Javascript console');");
    }

    @Test
    private void javascriptExample2() throws Exception {
        FirefoxDriver driver = new FirefoxDriver(new FirefoxProfile());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object response = js.executeScript("return console.log('I logged something to the Javascript console');");

    }

    @Test
    private void javascriptExample3() throws Exception {
        FirefoxDriver driver = new FirefoxDriver(new FirefoxProfile());
        String animal = "Lion";
        int seen = 5;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("console.log('I have seen a ' + arguments[0] + ' ' + arguments[1] + ' times(s)');", animal, seen);
    }

    @Test
    private void javascriptExample4() throws Exception {
        WebDriver driver = DriverFactory.getDriver();

        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("var callback = arguments[arguments.length - 1]; window.setTimeout(callback, 25000);");
    }

    @Test
    private void javascriptExample5() throws Exception {
        WebDriver driver = DriverFactory.getDriver();

        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("var callback = arguments[arguments.length - 1]; window.setTimeout(callback, 25000);");

        driver.get("http://www.google.com");
    }

    @Test
    private void javascriptExample6() throws Exception {
        WebDriver driver = DriverFactory.getDriver();

        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var callback = arguments[arguments.length - 1]; window.setTimeout(callback, 25000);");

        driver.get("http://www.google.com");
    }
}