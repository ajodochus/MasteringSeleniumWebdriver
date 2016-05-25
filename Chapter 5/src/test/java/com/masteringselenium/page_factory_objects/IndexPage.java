package com.masteringselenium.page_factory_objects;

import com.masteringselenium.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class IndexPage {

    @FindBy(how = How.CSS, using = "h1")
    private List<WebElement> heading;

    @FindBy(how = How.CSS, using = ".col-md-4 > p")
    private List<WebElement> mainText;

    @FindBy(how = How.CSS, using = ".btn")
    private List<WebElement> button;

    public PageHeader header = new PageHeader();
    public PageFooter footer = new PageFooter();

    public IndexPage() throws Exception {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public boolean mainTextIsDisplayed() {
        return mainText.size() == 1;
    }

    public boolean mainPageButtonIsDisplayed() {
        return button.size() == 1;

    }
}