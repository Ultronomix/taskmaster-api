package com.revature.taskmaster.common.screens;

import com.revature.taskmaster.common.util.AppContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WelcomeScreen extends AbstractScreen {

    public WelcomeScreen(BufferedReader consoleReader) {
        super("WelcomeScreen", consoleReader);
    }

    @Override
    public void render() throws IOException {

        System.out.println("+--------------------------------------------------------+");

        String welcomeMenu = "Welcome to Taskmaster!\n" +
                             "Please make a selection from the options below:\n" +
                             "1) Register\n" +
                             "2) Login\n" +
                             "3) Exit\n" +
                             "> ";

        System.out.print(welcomeMenu);

        String userSelection = consoleReader.readLine();

        switch (userSelection) {
            case "1":
                new RegisterScreen(consoleReader).render();
                break;
            case "2":
                new LoginScreen(consoleReader).render();
                break;
            case "3":
                System.out.println("Exiting the application!");
                AppContext.shutdown();
                break;
            default:
                System.out.println("You have made an incorrect selection. Please try again.");
        }
    }

}
