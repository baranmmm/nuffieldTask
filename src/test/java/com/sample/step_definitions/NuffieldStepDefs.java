package com.sample.step_definitions;

import com.sample.utilities.CommonSteps;
import com.sample.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class NuffieldStepDefs extends CommonSteps {

    public static String searchedPostcode = "";
    public static List<String> nearestGyms = new ArrayList<>();
    @Given("the user search for nearest gym")
    public void the_user_search_for_nearest_gym() {

        if(nuffieldHomePage.acceptCookiesButton.size()!=0){
            nuffieldHomePage.acceptCookiesButton.get(0).click();
        }
    }

    @When("the user search for {string} on searchbox")
    public void the_user_search_for_on_searchbox(String postCode) {
        searchedPostcode=postCode;
        nuffieldHomePage.searchBox.sendKeys(postCode);
        Actions action = new Actions(driver);
        waitFor(2);
        action.sendKeys(Keys.ENTER).build().perform();
        waitFor(1);
        for(int i=0; i<nuffieldHomePage.nearestGymList.size();i++){
            nearestGyms.add(nuffieldHomePage.nearestGymList.get(i).getText());
        }


    }

    @Then("the first result should be the nearest one")
    public void the_first_result_should_be_the_nearest_one() {
        WebDriver newDriver = Driver.setUp();
        newDriver.get("https://www.google.com/maps");
        if(googleMapsPage.acceptAllButton.size()!=0){
            googleMapsPage.acceptAllButton.get(0).click();
        }
        googleMapsPage.mapsSearchBox.sendKeys(searchedPostcode);
        waitForClickablility(googleMapsPage.directionsButton, 15).click();
        List<String> distances = new ArrayList<>();
        for(int i=0;i<nearestGyms.size();i++){
            waitFor(1);
            waitForVisibility(googleMapsPage.startingPoint,15).clear();
            googleMapsPage.startingPoint.sendKeys(nearestGyms.get(i));
            Actions action = new Actions(newDriver);
            action.sendKeys(Keys.ENTER).build().perform();
            waitForVisibility(googleMapsPage.distanceByWalkButton, 15).click();
            String distanceString = waitForVisibility(googleMapsPage.distance, 15).getText();
            distances.add(distanceString);
            waitFor(1);
        }

        String[] splittedfirstDistance = distances.get(0).split(" ");
        Double firstDistance = Double.parseDouble(splittedfirstDistance[0]);

        String[] splittedSecondDistance = distances.get(1).split(" ");
        Double secondDistance = Double.parseDouble(splittedSecondDistance[0]);

        Logger logger = Logger.getLogger("com.sample.step_definitions");
        logger.info("The distance from the searched location to the first result is "+ firstDistance+ " miles");
        logger.info("The distance from the searched location to the first result is "+ secondDistance+ " miles");
        if(firstDistance<secondDistance){
            logger.info("FIRST RESULT IS NEARER THAN THE SECOND RESULT.");
        }else {
            logger.info("FIRST RESULT IS NOT NEARER THAN THE SECOND RESULT.");
        }

        Assert.assertTrue(firstDistance<=secondDistance);
        newDriver.quit();
    }
}
