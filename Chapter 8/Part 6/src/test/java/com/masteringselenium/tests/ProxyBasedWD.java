package com.masteringselenium.tests;

import com.masteringselenium.DriverFactory;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


public class ProxyBasedWD extends DriverFactory {

    @Test
    public void usingAProxyToTrackNetworkTraffic() throws Exception {
        WebDriver driver = getBrowserMobProxyEnabledDriver();
        BrowserMobProxy browserMobProxy = getBrowserMobProxy();
        browserMobProxy.newHar();

        URL site = new URL("https://www.google.co.uk");
        driver.get(site.toString());

        Har httpArchive = browserMobProxy.getHar();

        assertThat(getHTTPStatusCode("https://www.google.co.uk:443/", httpArchive),
                is(equalTo(200)));

        browserMobProxy.abort();
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