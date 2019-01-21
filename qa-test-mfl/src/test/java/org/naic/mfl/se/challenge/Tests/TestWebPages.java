package org.naic.mfl.se.challenge.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.naic.mfl.se.challenge.Pages.WelcomePage;
import org.naic.mfl.se.challenge.ConfigUtil.BaseConfig;
import org.naic.mfl.se.challenge.Utility.ScreenShotListener;
import org.naic.mfl.se.challenge.Pages.CheckOutPage;
import org.naic.mfl.se.challenge.Pages.LoginPage;
import org.naic.mfl.se.challenge.Pages.SignInPage;
import org.naic.mfl.se.challenge.Utility.ReadWriteExcel;
import org.naic.mfl.se.challenge.Utility.UserInfo;
import org.naic.mfl.se.challenge.Utility.BaseLogger;
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
    WelcomePage welcomePage;
   // UserInfo user1;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser){
        testLog.info("choosing web browser");
        if(browser.equalsIgnoreCase("Firefox")){
            System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver");
            driver=new FirefoxDriver();
//            testLog.info("Firefox chosed.");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            driver=new ChromeDriver();
//            testLog.info("Chrome chosed.");
        }
        testLog.info(browser+" chosed.");

        driver.manage().window().maximize();
        driver.get(BaseConfig.Url);
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        testLog.info("test pages launched");
    }



    @Test(priority = 1)
    public void signinTest(){
        BaseLogger.startTestCase("SignIn Test ");

        String timestamp = String.valueOf(new Date().getTime());
        String newemail = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";


        signInPage=new SignInPage(driver);
        UserInfo user=new UserInfo(newemail);

        WebElement heading=signInPage.signinAction(user);
        welcomePage=new WelcomePage(driver);
        System.out.println(user.getFirstname()+" "+user.getLastname() );

        try {
            Assert.assertEquals(heading.getText(), "MY ACCOUNT");
            BaseLogger.info("MY ACCOUNT displayed");
            Assert.assertEquals(welcomePage.getDisplayedFullName(), user.getFirstname() + " " + user.getLastname());
            BaseLogger.info("fullname displayed");
            Assert.assertTrue(welcomePage.getAccountInfo().contains("Welcome to your account."));
            BaseLogger.info("\"Welcome to you accout\" displayed");
            Assert.assertTrue(welcomePage.isLogoutDisplayed());
            Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
            BaseLogger.info("Test Passed");
        } catch (Exception e){
            BaseLogger.error("Errors happened in signin test"+ e.getStackTrace());
        }
        signOut();

        BaseLogger.endTestCase("SignIn Test ");
    }

    @Test(priority = 2, dataProvider = "getTestData")
    public void loginTest(String email, String psw, String fullName) {

        BaseLogger.startTestCase("Log in Test ");
        loginPage=new LoginPage(driver);

        WebElement heading=loginPage.loginStep(email, psw);
        welcomePage=new WelcomePage(driver);

        try {
            Assert.assertEquals("MY ACCOUNT", heading.getText());
            BaseLogger.info("MY ACCOUNT displayed");
            Assert.assertEquals(fullName, welcomePage.getDisplayedFullName());
            BaseLogger.info("fullname displayed");
            Assert.assertTrue(welcomePage.getAccountInfo().contains("Welcome to your account."));
            BaseLogger.info("\"Welcome to you accout\" displayed");
            Assert.assertTrue(welcomePage.isLogoutDisplayed());
            Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
            BaseLogger.info("Test Passed");
        } catch (Exception e){
            BaseLogger.error("Errors happened in Log in test"+ e.getStackTrace());
        }

        signOut();
        BaseLogger.endTestCase("Log in Test ");

    }

    @Test(priority = 3)
    public void checkoutTest() throws Exception {
        checkOutPage=new CheckOutPage(driver);
        BaseLogger.startTestCase("CheckOut Test");
        ArrayList<Object[]> userlist=new ArrayList<Object[]>();
        userlist=ReadWriteExcel.readFromExcel(BaseConfig.ExcelFileName);

        WebElement heading=checkOutPage.checkOutStep((String)userlist.get(0)[0],(String)userlist.get(0)[1]);
        try {
            Assert.assertEquals("ORDER CONFIRMATION", heading.getText());
            BaseLogger.info("\"ORDER CONFIRMATION\" displayed");
            Assert.assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
            BaseLogger.info("\"Shipping\" displayed");
            Assert.assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
            BaseLogger.info("\"Payment\" displayed");
            Assert.assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText().contains("Your order on My Store is complete."));
            BaseLogger.info("\"Your order on My Store is complete.\" displayed");
            Assert.assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));

        }catch (Exception e){
            BaseLogger.error("Errors happened in checkout test"+e.getStackTrace());
        }

        welcomePage=new WelcomePage(driver);
        signOut();
        BaseLogger.endTestCase("CheckOut Test");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @DataProvider
    public Iterator<Object[]> getTestData(){
       // System.out.println();
        //String xmlfile="src/test/java/org/naic/mfl/se/challenge/Data/UserInfo.xlsx";
        ArrayList<Object[]> testData=null;

        try {
            testData=ReadWriteExcel.readFromExcel(BaseConfig.ExcelFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData.iterator();
    }

    public void signOut(){
        welcomePage.logoutClick();

    }
}
