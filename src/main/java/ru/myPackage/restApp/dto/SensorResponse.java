package ru.myPackage.restApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SensorResponse {

    List<SensorDTO> sensors;

}
