package com.todoist.pojo.selenium;

import com.todoist.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(css = "input[name='email']")
    private WebElement email;

    @FindBy(css = "input[name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement login;

    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String urfAfterlogin(){
        email.sendKeys(ConfigReader.readValueFromPropertyFile("username"));
        password.sendKeys(ConfigReader.readValueFromPropertyFile("password"));
        login.click();
        return driver.getCurrentUrl();
    }

}
