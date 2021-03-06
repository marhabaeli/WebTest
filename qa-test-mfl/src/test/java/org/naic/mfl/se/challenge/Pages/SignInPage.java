package org.naic.mfl.se.challenge.Pages;

import org.naic.mfl.se.challenge.ConfigUtil.BaseConfig;
import org.naic.mfl.se.challenge.Utility.PageUtil;
import org.naic.mfl.se.challenge.Utility.ReadWriteExcel;
import org.naic.mfl.se.challenge.Utility.UserInfo;
import org.naic.mfl.se.challenge.Utility.BaseLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignInPage {
WebDriver driver;
PageUtil pageUtil;
UserInfo newuser;


@FindBy(how=How.CLASS_NAME, using ="login" )     WebElement signin;
@FindBy(how=How.ID, using="email_create") WebElement create_email;
@FindBy(how = How.ID, using="SubmitCreate") WebElement submitcreate;
@FindBy(how=How.ID, using = "id_gender2") WebElement id_gender;
@FindBy(how = How.ID, using = "customer_firstname") WebElement firstname;
@FindBy(how = How.ID, using = "customer_lastname") WebElement lastname;
@FindBy(how=How.ID, using = "passwd") WebElement password;
@FindBy(how = How.ID, using = "days") WebElement days;
@FindBy(how=How.ID, using = "months") WebElement months;
@FindBy(how=How.ID, using = "years") WebElement years;
@FindBy(how = How.ID, using = "company") WebElement company;
@FindBy(how=How.ID, using = "address1") WebElement address1;
@FindBy(how=How.ID, using = "address2") WebElement address2;
@FindBy(how=How.ID, using = "city") WebElement city;
@FindBy(how=How.ID, using = "id_state") WebElement id_state;
@FindBy(how=How.ID, using = "postcode") WebElement postcode;
@FindBy(how=How.ID, using = "other") WebElement other;
@FindBy(how = How.ID, using = "phone") WebElement phone;
@FindBy(how = How.ID, using = "phone_mobile") WebElement phone_mobile;
@FindBy(how=How.ID, using = "alias") WebElement alias;
@FindBy(how = How.ID, using = "submitAccount") WebElement submitAccount;
@FindBy(how=How.CSS, using = "h1") WebElement h1;


public SignInPage(WebDriver driver){
    this.driver=driver;
    PageFactory.initElements(driver,this);
    pageUtil=new PageUtil(driver);
}

//define actions
public void createEmail(String email){
    pageUtil.waitForElementVisible(signin,BaseConfig.timeout);
    signin.click();
    BaseLogger.debug("sign in button clicked");

    create_email.sendKeys(email);
    BaseLogger.debug("user email entered");

    submitcreate.click();
    BaseLogger.debug("addtocart button clicked");
    pageUtil.waitForElementVisible(id_gender,BaseConfig.timeout);
}


public void fillUserInfo(UserInfo user){

    BaseLogger.info("entering user info started... ");
    pageUtil.waitForElementVisible(id_gender,BaseConfig.timeout);
    id_gender.click();
    firstname.sendKeys(user.getFirstname());
    lastname.sendKeys(user.getLastname());
    password.sendKeys(user.getPassword());
    Select select=new Select(days);
    select.selectByValue(user.getDays());

    select=new Select(months);
    select.selectByValue(user.getMonths());

    select=new Select(years);
    select.selectByValue(user.getYears());

    company.sendKeys(user.getCompany());
    address1.sendKeys(user.getAddress1());
    address2.sendKeys(user.getAddress2());
    city.sendKeys(user.getCity());

    select=new Select(id_state);
    select.selectByVisibleText(user.getState());
    postcode.sendKeys(user.getPostcode());
    other.sendKeys(user.getOther());
    phone.sendKeys(user.getPhone());
    phone_mobile.sendKeys(user.getPhone_mobile());
    alias.clear();
    alias.sendKeys(user.getAlias());

    BaseLogger.info("entering user info ended");

    //*************
    String projectPath=System.getProperty("user.dir");
    System.out.println(projectPath);
//*************

    //write user info to Excel file
    //String ExcelFileName="src/test/java/org/naic/mfl/se/challenge/Data/UserInfo.xlsx";
    try {
        ReadWriteExcel.write2Excel(BaseConfig.ExcelFileName, user);
    } catch (Exception e) {
        e.printStackTrace();
    }
    BaseLogger.info("user info were written to excel");

    submitAccount.click();
    BaseLogger.debug("addtocart button clicked");
    pageUtil.waitForElementVisible(h1,BaseConfig.timeout);

}

    public WebElement signinAction(UserInfo user){

        createEmail(user.getEmail());
        fillUserInfo(user);
        pageUtil.waitForElementVisible(h1,BaseConfig.timeout);
        return (h1);
    }

}
