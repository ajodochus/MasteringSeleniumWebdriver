package com.masteringselenium.fluent_page_objects;

import com.masteringselenium.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(how = How.ID, using = "username")
    private WebElement usernameLocator;

    @FindBy(how = How.ID, using = "password")
    private WebElement passwordLocator;

    @FindBy(how = How.ID, using = "login")
    private WebElement loginButtonLocator;

    public LoginPage() throws Exception {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public LoginPage enterUsername(String username) {
        usernameLocator.sendKeys(username);

        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordLocator.sendKeys(password);

        return this;
    }

    public void andLogin() {
        loginButtonLocator.click();
    }
}