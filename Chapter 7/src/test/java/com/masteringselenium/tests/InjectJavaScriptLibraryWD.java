package com.masteringselenium.tests;

import com.masteringselenium.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class InjectJavaScriptLibraryWD extends DriverFactory {

    public void injectScript(String scriptURL) throws Exception {
        WebDriver driver = getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("function injectScript(url) {\n" +
                "    var script = document.createElement('script');\n" +
                "    script.src = url;\n" +
                "    var head = document.getElementsByTagName('head')[0];\n" +
                "    head.appendChild(script);\n" +
                "}\n" +
                "\n" +
                "var scriptURL = arguments[0];\n" +
                "injectScript(scriptURL);"
                , scriptURL);
    }

    public Boolean isjQueryLoaded() throws Exception {
        WebDriver driver = getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return typeof jQuery != 'undefined';");
    }

    @Test
    public void injectjQueryIntoGoogle() throws Exception {

        WebDriver driver = DriverFactory.getDriver();

        driver.get("http://www.google.com");

        assertThat(isjQueryLoaded(), is(equalTo(false)));

        injectScript("https://code.jquery.com/jquery-latest.min.js");

        assertThat(isjQueryLoaded(), is(equalTo(true)));
    }
}