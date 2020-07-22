package com.udacity.jwdnd.course1.cloudstorage.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"logoutDiv\"]//button")
    private WebElement logOutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    public void logOut(){
        logOutButton.click();
    }

    public WebElement getNotesTab() {
        return notesTab;
    }

    public WebElement getCredentialsTab() {
        return credentialsTab;
    }

    public void openNotesTab(){
        notesTab.click();
    }

    public void openCredentialsTab(){
        credentialsTab.click();
    }

    // Notes Tab

    @FindBy(id = "add-note")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "save-note")
    private WebElement noteSubmit;

    @FindBy(xpath = "//*[@id=\"nav-notes\"]//tbody/tr/td/button")
    private WebElement editNoteButton;

    @FindBy(xpath = "//*[@id=\"nav-notes\"]//tbody/tr/td/a")
    private WebElement deleteNoteButton;

    @FindBy(xpath = "//*[@id=\"nav-notes\"]//tbody/tr/th")
    private WebElement noteTitleText;

    @FindBy(xpath = "//*[@id=\"nav-notes\"]//tbody/tr/td[2]")
    private WebElement noteDescriptionText;

    public WebElement getAddNoteButton() {
        return addNoteButton;
    }

    public WebElement getNoteSubmit() {
        return noteSubmit;
    }

    public void createNewNote(String title, String description){
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        noteSubmit.click();
    }

    public WebElement getNoteTitleText() {
        return noteTitleText;
    }

    public String getNoteTitleTextValue() {
        return noteTitleText.getText();
    }

    public String getNoteDescriptionTextValue() {
        return noteDescriptionText.getText();
    }

    // Credentials Tab

}
