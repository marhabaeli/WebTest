package org.naic.mfl.se.challenge.Pages;

import org.naic.mfl.se.challenge.Utility.Config;
import org.naic.mfl.se.challenge.Utility.PageUtil;
import org.naic.mfl.se.challenge.logger.BaseLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
    WebDriver driver;
    PageUtil pageUtil;
    LoginPage loginPage;

    @FindBy(linkText = "Women") WebElement women;
    @FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img") WebElement image;
    @FindBy(name = "Submit") WebElement submit;
    @FindBy(linkText = "Proceed to checkout") WebElement proceedtocheckout;
  //  @FindBy(linkText = "Proceed to checkout")
    @FindBy(name = "processAddress") WebElement processAddress;
    @FindBy(id="uniform-cgv") WebElement uniform_cgv;
    @FindBy(name="processCarrier") WebElement processcarrier;
    @FindBy(className = "bankwire") WebElement bankwire;
    @FindBy(xpath = "//*[@id='cart_navigation']/button") WebElement cart_nav_button;
    @FindBy(css = "h1") WebElement h1;

    public CheckOutPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
        pageUtil=new PageUtil(driver);
        loginPage=new LoginPage(driver);
    }

    //define Action
    public void choseAndCheckout(){
        pageUtil.waitForElementVisible(women,Config.timeout);
        women.click();
        BaseLogger.debug("women section clicked");

        image.click();
        BaseLogger.debug("one image clicked");

        pageUtil.waitForElementVisible(submit,Config.timeout);
        submit.click();
        BaseLogger.debug("submit button clicked");

        BaseLogger.info("paying process started");

        pageUtil.waitForElementVisible(proceedtocheckout,Config.timeout);
        proceedtocheckout.click();

        //****
        pageUtil.waitForElementVisible(proceedtocheckout,Config.timeout);
        proceedtocheckout.click();

        pageUtil.waitForElementVisible(processAddress,Config.timeout);
        processAddress.click();

        pageUtil.waitForElementVisible(uniform_cgv,Config.timeout);

        processcarrier.click();

        pageUtil.waitForElementVisible(bankwire, Config.timeout);
        bankwire.click();

        pageUtil.waitForElementVisible(cart_nav_button,Config.timeout);
        cart_nav_button.click();
        BaseLogger.info("paying process ended");
    }

    public WebElement checkOutStep(String useremail, String psw){
        loginPage.loginStep(useremail, psw);
        choseAndCheckout();
        pageUtil.waitForElementVisible(h1,Config.timeout);
        return h1;
    }

}
