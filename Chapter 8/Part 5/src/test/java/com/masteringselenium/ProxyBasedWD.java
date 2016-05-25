package com.masteringselenium;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


public class ProxyBasedWD {

    private static WebDriver driver;

    @AfterSuite
    public static void cleanUpDriver() {
        driver.close();
    }

    @Test
    public void usingAProxyToTrackNetworkTraffic() throws Exception {
        BrowserMobProxy browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.start();
        Proxy seleniumProxyConfiguration = ClientUtil.createSeleniumProxy(browserMobProxy);

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability(CapabilityType.PROXY, seleniumProxyConfiguration);
        driver = new FirefoxDriver(desiredCapabilities);

        browserMobProxy.newHar();

        driver.get("https://www.google.co.uk");

        Har httpArchive = browserMobProxy.getHar();

        assertThat(getHTTPStatusCode("https://www.google.co.uk:443/", httpArchive),
                is(equalTo(200)));
    }

    public int getHTTPStatusCode(String expectedURL, Har httpArchive) {
        for (HarEntry entry : httpArchive.getLog().getEntries()) {
            if (entry.getRequest().getUrl().equals(expectedURL)) {
                return entry.getResponse().getStatus();
            }
        }

        return 0;
    }

}