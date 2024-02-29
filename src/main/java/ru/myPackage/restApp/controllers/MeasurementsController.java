package ru.myPackage.restApp.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.myPackage.restApp.dto.MeasurementsDTO;
import ru.myPackage.restApp.dto.MeasurementsResponse;
import ru.myPackage.restApp.models.Measurements;
import ru.myPackage.restApp.models.Sensor;
import ru.myPackage.restApp.services.MeasurementsService;
import ru.myPackage.restApp.services.SensorService;
import ru.myPackage.restApp.utils.MeasurementsException;
import ru.myPackage.restApp.utils.SensorErrorResponse;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementsController {

    private final ModelMapper modelMapper;
    private final MeasurementsService measurementsService;
    private final SensorService sensorService;

    @GetMapping("/{id}")
    public MeasurementsResponse getAll(@PathVariable("id") int id) {
        Sensor sensor = sensorService.findOne(id);

        return new MeasurementsResponse(measurementsService.findAllByOwner(sensor)
                .stream()
                .map(this::convertToMeasurementsDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}/rainyDaysCount")
    public MeasurementsResponse getRainyDays(@PathVariable("id") int id) {
        Sensor sensor = sensorService.findOne(id);

        return new MeasurementsResponse(measurementsService.findAllByOwner(sensor, true)
                .stream()
                .map(this::convertToMeasurementsDTO)
                .collect(Collectors.toList()));
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(MeasurementsException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private MeasurementsDTO convertToMeasurementsDTO(Measurements measurements) {
        return modelMapper.map(measurements, MeasurementsDTO.class);
    }

    private Measurements convertToMeasurements(MeasurementsDTO measurementsDTO) {
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

}
