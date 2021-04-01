package com.todoist.util;

import com.todoist.pojo.selenium.LoginPage;
import com.todoist.stepdefinitions.Presteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuthTokenGenerator {
    private Logger log = LogManager.getLogger();

    /**
     * Getting Token using the client_id and client_secret
     * @return the bearer token
     */
    public String getTokenUsingClientDetails() {
        String authorizationCode = getAuthorizationCode();
        String bearerToken = getTokenFromAuthorizationCode(authorizationCode);
        return bearerToken;
    }

    /**
     * Generate Token using the authorization code provided
     * @param code - the authorization code
     * @return bearer token
     */
    private String getTokenFromAuthorizationCode(String code) {
        String bearerToken = new Presteps().getToken(code);
        return bearerToken;
    }

    private String getAuthorizationCode() {
        String url = launchBrowserAndGetRedirectedUrl();
        String authorizationCode = url.split("=")[1].split("&")[0];
        log.info("Authorization Code = " + authorizationCode);
        return authorizationCode;
    }

    /**
     * launch the browser and authencticate to get the code
     * @return the redirected url after successful login
     */
    private String launchBrowserAndGetRedirectedUrl() {
        String authUrl = ConfigReader.readValueFromPropertyFile("authUrl");
        String redirect_uri = ConfigReader.readValueFromPropertyFile("redirect_uri");
        String state = new TestUtil().getRandomString();
        String scope = ConfigReader.readValueFromPropertyFile("scope");
        String client_id = ConfigReader.readValueFromPropertyFile("clientId");
        String completeUrl = authUrl + "?" + "response_type=code&state=" + state + "&client_id=" + client_id + "&scope=" + scope + "&redirect_uri=" + redirect_uri;

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get(completeUrl);
        LoginPage loginPage = new LoginPage(driver);

        String urlContainingCode = loginPage.urfAfterlogin();

        try {
            if(driver != null)  driver.quit();
        } catch (Exception e) {
            log.info("Unable to quit the browser session");
        }
        return urlContainingCode;
    }
}
