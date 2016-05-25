package com.masteringselenium.page_factory_objects;

import com.masteringselenium.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PageHeader {

    @FindBy(how = How.CSS, using = ".nav li:nth-child(1) > a")
    private WebElement servicesLink;

    @FindBy(how = How.CSS, using = ".nav li:nth-child(2) > a")
    private WebElement contactLink;

    public PageHeader() throws Exception {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public void goToTheServicesPage() {
        servicesLink.click();
    }

    public void goToTheContactPage() {
        contactLink.click();
    }
}