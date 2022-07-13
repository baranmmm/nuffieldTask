package com.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NuffieldHomePage extends CommonPageElements{

    @FindBy(id="ccc-notify-accept")
    public List<WebElement> acceptCookiesButton;

    @FindBy(css = "[placeholder=\"Search by postcode, town or area\"]")
    public WebElement searchBox;

    @FindBy(css = "[itemname=\"postalCode\"]")
    public List<WebElement> nearestGymList;
}
