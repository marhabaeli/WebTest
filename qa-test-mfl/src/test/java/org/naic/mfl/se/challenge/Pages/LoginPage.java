package org.naic.mfl.se.challenge.Pages;

import org.naic.mfl.se.challenge.Utility.Config;
import org.naic.mfl.se.challenge.Utility.PageUtil;
import org.naic.mfl.se.challenge.logger.BaseLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    WebDriver driver;
    PageUtil pageUtil;
    @FindBy(className = "login") WebElement singin;
    @FindBy(id="email")     WebElement email;
    @FindBy(id="passwd")    WebElement password;
    @FindBy(id="SubmitLogin") WebElement submitlogin;
    @FindBy(css = "h1") WebElement h1;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        pageUtil=new PageUtil(driver);
    }

    //define action
    public WebElement loginStep(String useremail, String psw){
        pageUtil.waitForElementVisible(singin,Config.timeout);
        singin.click();
        BaseLogger.debug("sign in button clicked");

        email.sendKeys(useremail);
        BaseLogger.debug("user email entered");
        password.sendKeys(psw);
        BaseLogger.debug("user password entered");
        submitlogin.click();
        BaseLogger.debug("submit button clicked");

        pageUtil.waitForElementVisible(h1,Config.timeout);
        return h1;
    }
}
