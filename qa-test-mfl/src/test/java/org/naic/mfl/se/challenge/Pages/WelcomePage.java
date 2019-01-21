package org.naic.mfl.se.challenge.Pages;

import org.naic.mfl.se.challenge.Utility.PageUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {
    WebDriver driver;
    PageUtil pageUtil;


    @FindBy(how = How.CLASS_NAME, using = "account")     WebElement account;
    @FindBy(how=How.CLASS_NAME, using = "info-account") WebElement info_account;
    @FindBy(how = How.CLASS_NAME, using = "logout") WebElement logout;

    public WelcomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        pageUtil=new PageUtil(driver);
    }

    //Action definition
    public boolean isLogoutDisplayed(){
        return logout.isDisplayed();
    }

    public void logoutClick(){
        logout.click();
    }

    public String getAccountInfo(){
        return info_account.getText();
    }

    public String getDisplayedFullName(){
       // System.out.println(account.getText());
        return account.getText();
    }
}
