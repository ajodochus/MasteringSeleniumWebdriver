package com.masteringselenium.tests;

import com.masteringselenium.DriverFactory;
import com.masteringselenium.fluent_page_objects.LoginPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class FluentGenericLoginTestWD extends DriverFactory {

    @Test
    public void logInToTheWebsite() throws Exception {
        getDriver().get("http://ch5.masteringselenium.com/index.html");
        loginPage = new LoginPage();

        loginPage.enterUsername("foo")
                .enterPassword("bar")
                .andLogin();

        assertThat(getDriver().getTitle(), is(equalTo("Logged in")));
    }

    @Test
    public void logInToTheWebsiteWithClientSideValidationCheck() throws Exception {
        getDriver().get("http://ch5.masteringselenium.com/index.html");
        loginPage = new LoginPage();

        loginPage.enterUsername("foo")
                .enterPassword("bar");

        //TODO Perform client side validation check here

        loginPage.andLogin();

        assertThat(getDriver().getTitle(), is(equalTo("Logged in")));
    }

}
