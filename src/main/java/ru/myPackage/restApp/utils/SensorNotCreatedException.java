package ru.myPackage.restApp.utils;

public class SensorNotCreatedException extends RuntimeException{

    public SensorNotCreatedException() {

    }

    public SensorNotCreatedException(String message) {
        super(message);
    }

}
