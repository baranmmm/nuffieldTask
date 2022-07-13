package com.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleMapsPage extends CommonPageElements{

    @FindBy(id = "searchboxinput")
    public WebElement mapsSearchBox;

    @FindBy(id = "hArJGc")
    public WebElement directionsButton;

    @FindBy(xpath = "(//input[@class=\"tactile-searchbox-input\"])[4]")
    public WebElement startingPoint;

    @FindBy(xpath = "(//button[.='Accept all'])[1]")
    public List<WebElement> acceptAllButton;

    @FindBy(xpath = "((//div[@class=\"XdKEzd\"])[1]//div)[2]")
    public WebElement distance;

    @FindBy(xpath = "//img[@aria-label=\"Walking\"]//..")
    public WebElement distanceByWalkButton;
}
