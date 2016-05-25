package com.masteringselenium.tests;

import com.masteringselenium.DriverFactory;
import com.masteringselenium.page_objects.AboutPage;
import com.masteringselenium.page_objects.IndexPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class NavigateToAboutPageWD extends DriverFactory {

    @Test
    public void goToTheAboutPageStep1() throws Exception {
        getDriver().get("http://ch5.masteringselenium.com/index.html");
        getDriver().findElement(By.cssSelector(".left-footer > a")).click();
        WebElement element = getDriver().findElement(By.cssSelector("h1"));

        assertThat(element.getText(), is(equalTo("About us!")));
    }

    @Test
    public void goToTheAboutPageStep2() throws Exception {
        getDriver().get("http://ch5.masteringselenium.com/index.html");

        WebElement aboutLink = getDriver().findElement(By.cssSelector(".left-footer > a"));

        aboutLink.click();

        WebElement aboutHeading = getDriver().findElement(By.cssSelector("h1"));

        assertThat(aboutHeading.getText(), is(equalTo("About us!")));
    }

    @Test
    public void goToTheAboutPageStep3() throws Exception {
        getDriver().get("http://ch5.masteringselenium.com/index.html");

        WebElement aboutLink = getDriver().findElement(IndexPage.aboutLinkLocator);

        aboutLink.click();

        WebElement aboutHeading = getDriver().findElement(AboutPage.aboutHeadingLocator);

        assertThat(aboutHeading.getText(), is(equalTo("About us!")));
    }

}
