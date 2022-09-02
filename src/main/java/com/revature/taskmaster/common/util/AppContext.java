package com.revature.taskmaster.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppContext {

    private static boolean appRunning;
    private BufferedReader consoleReader;

    public AppContext() {
        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void startApp() {
        while (appRunning) {
            try {
                System.out.println("The app is started, but will close immediately.");
                appRunning = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void shutdown() {
        appRunning = false;
    }


}
