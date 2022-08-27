package com.revature.taskmaster.common.screens;

import java.io.BufferedReader;

public abstract class AbstractScreen implements Screen {

    protected String name;
    protected BufferedReader consoleReader;

    public AbstractScreen(String name, BufferedReader consoleReader) {
        this.name = name;
        this.consoleReader = consoleReader;
    }

    public String getName() {
        return name;
    }

}
