package org.naic.mfl.se.challenge.Utility;

import org.apache.commons.io.FileUtils;
import org.naic.mfl.se.challenge.Tests.TestWebPages;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class ScreenShotListener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {
        if(iTestResult.getStatus()==ITestResult.FAILURE){

            String fileseparator=System.getProperty("file.separator");
            String imagepath="FailTestScreenshots";

            File imagedir=new File(imagepath);
            if(!imagedir.exists()) {

                imagedir.mkdir();

            }

            TakesScreenshot ts=(TakesScreenshot)TestWebPages.driver;
            File scrFile=ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File(imagedir+fileseparator+iTestResult.getName()+"_"+ Arrays.toString(iTestResult.getParameters())+".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
