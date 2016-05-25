package com.masteringselenium.tests;

import com.masteringselenium.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class cssMenuWD extends DriverFactory {

    @Test
    public void automateCSSMenu() throws Exception {
        WebDriver driver = getDriver();

        driver.get("http://ch6.masteringselenium.com/cssMenu.html");
        Actions advancedActions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5, 100);

        WebElement servicesMenuOption = driver.findElement(By.id("services"));
        WebElement webDevelopmentSubMenuOption = driver.findElement(By.cssSelector("#services > ul > li:nth-child(2)"));

        advancedActions.moveToElement(servicesMenuOption)
                .perform();

        wait.until(ExpectedConditions.visibilityOf(webDevelopmentSubMenuOption));

        advancedActions.moveToElement(webDevelopmentSubMenuOption)
                .click()
                .perform();

    }
}
