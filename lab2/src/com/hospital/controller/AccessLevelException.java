package com.hospital.controller;

public class AccessLevelException extends Exception {
    public AccessLevelException() {
        super();
    }

    public AccessLevelException(String message) {
        super(message);
    }
}
