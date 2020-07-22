package com.udacity.jwdnd.course1.cloudstorage.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//*[@id=\"logoutDiv\"]//button")
    private WebElement logOutButton;


    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void logOut(){
        logOutButton.click();
    }

}
