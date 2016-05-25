package com.masteringselenium.tests;

import com.masteringselenium.DriverFactory;
import com.masteringselenium.downloader.FileDownloader;
import com.masteringselenium.downloader.RequestType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class FileDownloaderWD extends DriverFactory {

    private static JettyServer localWebServer;
    private static final String WEB_SERVER_URL = "http://localhost";
    private static final int WEB_SERVER_PORT = 9081;
    private static URI downloadURI200;

    @BeforeSuite
    public static void start() throws Exception {
        localWebServer = new JettyServer(WEB_SERVER_PORT);
        downloadURI200 = new URI(WEB_SERVER_URL + ":" + WEB_SERVER_PORT + "/downloadTest.html");
    }

    @AfterSuite
    public static void stop() throws Exception {
        localWebServer.stopJettyServer();
    }

    @Test
    public void statusCodeFromEmbeddedFile() throws Exception {
        WebDriver driver = getDriver();
        FileDownloader downloadHandler = new FileDownloader(driver);

        driver.get(downloadURI200.toString());
        WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
        URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));

        downloadHandler.setURI(fileAsURI);
        downloadHandler.setHTTPRequestMethod(RequestType.GET);

        assertThat(downloadHandler.getLinkHTTPStatus(), is(equalTo(200)));
    }
}