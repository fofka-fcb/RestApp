package ru.myPackage.restApp.dto;

import lombok.Getter;
import lombok.Setter;
import ru.myPackage.restApp.models.Sensor;

@Getter
@Setter
public class MeasurementsDTO {

    private Double temp;

    private Boolean raining;

    private Sensor owner;

}
