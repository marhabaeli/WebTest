package org.naic.mfl.se.challenge.Pages;

import org.naic.mfl.se.challenge.ConfigUtil.BaseConfig;
import org.naic.mfl.se.challenge.Utility.PageUtil;
import org.naic.mfl.se.challenge.Utility.BaseLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
    WebDriver driver;
    PageUtil pageUtil;
    LoginPage loginPage;

    @FindBy(linkText = "Women") WebElement women;
    @FindBy(xpath = "//*[@id='center_column']/ul/li[1]/div/div[1]/div/a[1]/img") WebElement image;
    @FindBy(name = "Submit") WebElement addtocart;
    @FindBy(linkText = "Proceed to checkout") WebElement proceedtocheckout;
  //  @FindBy(linkText = "Proceed to checkout")
    @FindBy(name = "processAddress") WebElement processAddress;
    @FindBy(id="uniform-cgv") WebElement uniform_cgv;
//    @FindBy(id = "cgv") WebElement uniform_cgv;
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
        pageUtil.waitForElementVisible(women,BaseConfig.timeout);
        women.click();
        BaseLogger.debug("women section was clicked");

        image.click();
        BaseLogger.debug("one image was clicked");

        int iframes=driver.findElements(By.xpath("//iframe")).size();
        if(iframes>0)
            driver.switchTo().frame(0);

        pageUtil.waitForElementVisible(addtocart,BaseConfig.timeout);
        addtocart.click();
        BaseLogger.debug("add to cart button clicked");

        if(iframes>0)
            driver.switchTo().defaultContent();
        BaseLogger.info("product successfully added to account\'s cart");
      //  BaseLogger.info("paying process started");

        pageUtil.waitForElementVisible(proceedtocheckout,BaseConfig.timeout);
        //proceedtocheckout.click();
        proceedtocheckout.sendKeys(Keys.RETURN);
        BaseLogger.info("Proceed to checkout clicked");


        //****
        BaseLogger.info("shopping summary displayed");
        pageUtil.waitForElementVisible(proceedtocheckout,BaseConfig.timeout);
//        proceedtocheckout.click();
        proceedtocheckout.sendKeys(Keys.RETURN);


        pageUtil.waitForElementVisible(processAddress,BaseConfig.timeout);
        processAddress.click();
        BaseLogger.info("account\'s address confirmed");

        pageUtil.waitForElementVisible(uniform_cgv,BaseConfig.timeout);
        uniform_cgv.click();
        BaseLogger.info("Terms of Services checked");

        processcarrier.click();

        BaseLogger.info("bank service processing...");

        pageUtil.waitForElementVisible(bankwire, BaseConfig.timeout);
        bankwire.click();

        BaseLogger.info("Bank wire chosen");

        pageUtil.waitForElementVisible(cart_nav_button,BaseConfig.timeout);
        BaseLogger.info("payment way confirmed");

        cart_nav_button.click();
        BaseLogger.info("paying process ended");
    }

    public WebElement checkOutStep(String useremail, String psw){
        loginPage.loginStep(useremail, psw);
        choseAndCheckout();
        pageUtil.waitForElementVisible(h1,BaseConfig.timeout);
        return h1;
    }

}
