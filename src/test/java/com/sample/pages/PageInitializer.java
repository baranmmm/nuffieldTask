package com.sample.pages;

import com.sample.utilities.Driver;

public class PageInitializer extends Driver {

    public static NuffieldHomePage nuffieldHomePage;
    public static GoogleMapsPage googleMapsPage;


    public static void initialize() {

        nuffieldHomePage = new NuffieldHomePage();
        googleMapsPage = new GoogleMapsPage();

    }
}
