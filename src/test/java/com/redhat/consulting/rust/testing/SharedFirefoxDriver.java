package com.redhat.consulting.rust.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by mrice on 8/1/14.
 */
public class SharedFirefoxDriver {

    private static WebDriver driver;

    public static synchronized WebDriver currentDriver() {
        if (driver == null) {
            try {
                driver = new FirefoxDriver();
            } finally {
                try { //added this
                    Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
                } catch (Exception e) { }
            }
        }
        return driver;
    }

    private static class BrowserCleanup implements Runnable {
        @Override
        public void run() {
            close();
        }
    }

    public static synchronized void close() {
        if (driver != null) { //added this
            driver.close();
            driver.quit();
            driver = null;  //necessary tear down this instance
        }
    }

}
