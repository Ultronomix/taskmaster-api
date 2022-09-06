package com.revature.taskmaster.common.exceptions;

public class DataSourceException extends RuntimeException {

    public DataSourceException(Throwable cause) {
        super("Something went wrong when communicating with the database. Developers please check logs for more details.", cause);
    }

    public DataSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
