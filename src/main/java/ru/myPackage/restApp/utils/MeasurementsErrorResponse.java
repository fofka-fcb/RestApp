package ru.myPackage.restApp.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MeasurementsErrorResponse {

    private String message;

    private long timestamp;

}
