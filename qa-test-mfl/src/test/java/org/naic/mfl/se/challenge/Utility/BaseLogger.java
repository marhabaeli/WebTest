package org.naic.mfl.se.challenge.Utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class BaseLogger {

    private static Logger Log = LogManager.getLogger(BaseLogger.class);//


    public static void startTestCase(String sTestCaseName){

        Log.info("*************  "+sTestCaseName+ "  Started     ************");

    }

    public static void endTestCase(String sTestCaseName){

        Log.info("*****************     "+sTestCaseName+" ended     ****************");

    }

    public static void info(String message) {

        Log.info(message);

    }

    public static void warn(String message) {

        Log.warn(message);

    }

    public static void error(String message) {

        Log.error(message);

    }

    public static void fatal(String message) {

        Log.fatal(message);

    }

    public static void debug(String message) {

        Log.debug(message);

    }
}
