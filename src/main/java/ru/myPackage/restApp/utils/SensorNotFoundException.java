package ru.myPackage.restApp.utils;

public class SensorNotFoundException extends RuntimeException{

    public SensorNotFoundException() {

    }

    public SensorNotFoundException(String message) {
        super(message);
    }
}
