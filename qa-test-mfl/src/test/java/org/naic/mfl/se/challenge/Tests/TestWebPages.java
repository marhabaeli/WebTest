package org.naic.mfl.se.challenge.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.naic.mfl.se.challenge.scrListener.ScreenShotListener;
import org.naic.mfl.se.challenge.Pages.CheckOutPage;
import org.naic.mfl.se.challenge.Pages.LoginPage;
import org.naic.mfl.se.challenge.Pages.SignInPage;
import org.naic.mfl.se.challenge.Utility.DataUtil;
import org.naic.mfl.se.challenge.Utility.UserInfo;
import org.naic.mfl.se.challenge.logger.BaseLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

@Listeners(ScreenShotListener.class)
public class TestWebPages {
    public static WebDriver driver;
    Logger testLog=LogManager.getLogger(TestWebPages.class);
    LoginPage loginPage;
    CheckOutPage checkOutPage;
    SignInPage signInPage;
    UserInfo user1;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser){
        testLog.info("choosing web browser");
        if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe");
            driver=new FirefoxDriver();
            testLog.info("Firefox chosed.");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver=new ChromeDriver();
            testLog.info("Chrome chosed.");
        }

        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        testLog.info("test pages launched");
    }

    private void setUpUser(String id_gender, String frsname, String lstname, String eml,
                           String psw, String dys, String mnth, String yrs, String cpn, String add1,
                           String add2, String cty, String sta, String pstn, String other,
                           String phn, String mbl, String als){
        user1=new UserInfo();
       // user1.setId_gender();
        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";

        user1.setFirstname(frsname);
        user1.setLastname(lstname);
        user1.setEmail(email);
        user1.setPassword(psw);
        user1.setDays(dys);
        user1.setMonths(mnth);
        user1.setYears(yrs);
        user1.setCompany(cpn);
        user1.setAddress1(add1);
        user1.setAddress2(add2);
        user1.setCity(cty);
        user1.setState(sta);
        user1.setPostcode(pstn);
        user1.setOther(other);
        user1.setPhone(phn);
        user1.setPhone_mobile(mbl);
        user1.setAlias(als);
    }


    @Test(priority = 1, dataProvider = "getTestData")
    public void signinTest(String id_gender, String frsname, String lstname, String email,
                           String psw, String dys, String mnth, String yrs, String cpn, String Add1,
                           String add2, String cty, String sta, String pstn, String other,
                           String phn, String mbl, String als){
        BaseLogger.startTestCase("SignIn Test");

        setUpUser(id_gender, frsname, lstname, email, psw, dys, mnth, yrs, cpn, Add1,
                add2, cty, sta, pstn, other, phn,  mbl,  als);

        signInPage=new SignInPage(driver);

        WebElement heading=signInPage.signinAction(user1.getEmail(),user1);


        try {
            Assert.assertEquals(heading.getText(), "MY ACCOUNT");
            Assert.assertEquals(driver.findElement(By.className("account")).getText(), user1.getFirstname() + " " + user1.getLastname());
            Assert.assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
            Assert.assertTrue(driver.findElement(By.className("logout")).isDisplayed());
            Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
        } catch (Exception e){
            BaseLogger.error("Errors happened in signin test"+ e.getStackTrace());
        }

        BaseLogger.endTestCase("SignIn Test");
    }

    @Test(priority = 2)
    public void loginTest(){

        String fullName = "Joe Black";

        BaseLogger.startTestCase("Log in Test");
        LoginPage loginPage=new LoginPage(driver);

        WebElement heading=loginPage.loginStep(user1.getEmail(), user1.getPassword());


        try {
            Assert.assertEquals("MY ACCOUNT", heading.getText());
            Assert.assertEquals(fullName, driver.findElement(By.className("account")).getText());
            Assert.assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
            Assert.assertTrue(driver.findElement(By.className("logout")).isDisplayed());
            Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
        } catch (Exception e){
            BaseLogger.error("Errors happened in Log in test"+ e.getStackTrace());
        }

        BaseLogger.endTestCase("Log in Test");

    }

    @Test(priority = 3)
    public void checkoutTest(){
        checkOutPage=new CheckOutPage(driver);
        BaseLogger.startTestCase("CheckOutPage");
        WebElement heading=checkOutPage.checkOutStep(user1.getEmail(),user1.getAddress1());
        try {
            Assert.assertEquals("ORDER CONFIRMATION", heading.getText());
            Assert.assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText().contains("Your order on My Store is complete."));
            Assert.assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));

        }catch (Exception e){
            BaseLogger.error("Errors happened in checkout test"+e.getStackTrace());
        }
    }

    @DataProvider
    public Iterator<Object[]> getTestData(){
        String xmlfile="/src/test/java/org/naic/mfl/se/challenge/Data/UserInfo.xlsx";
        ArrayList<Object[]> testData=null;
        try {
            testData=DataUtil.readFromExcel(xmlfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData.iterator();
    }
}
