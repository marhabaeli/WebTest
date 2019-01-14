package org.naic.mfl.se.challenge.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class BaseLogger {

    private static Logger Log = LogManager.getLogger(BaseLogger.class);//


    public static void startTestCase(String sTestCaseName){

        Log.info("*************  "+sTestCaseName+ "       ************");

    }

    public static void endTestCase(String sTestCaseName){

        Log.info("*****************     "+sTestCaseName+" end     ****************");

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
