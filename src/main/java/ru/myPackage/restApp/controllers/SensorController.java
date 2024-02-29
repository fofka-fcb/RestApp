package ru.myPackage.restApp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.myPackage.restApp.dto.SensorDTO;
import ru.myPackage.restApp.dto.SensorResponse;
import ru.myPackage.restApp.models.Sensor;
import ru.myPackage.restApp.services.SensorService;
import ru.myPackage.restApp.utils.SensorErrorResponse;
import ru.myPackage.restApp.utils.SensorNotCreatedException;
import ru.myPackage.restApp.utils.SensorNotFoundException;
import ru.myPackage.restApp.utils.SensorValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensor")
@RequiredArgsConstructor
public class SensorController {

    private final ModelMapper modelMapper;
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @GetMapping
    public SensorResponse getSensors() {
//        return sensorService.findAll().stream().map(this::convertToSensorDTO).collect(Collectors.toList());
        return new SensorResponse(sensorService.findAll()
                .stream()
                .map(this::convertToSensorDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public SensorDTO getSensorDTO(@PathVariable("id") int id) {
        return convertToSensorDTO(sensorService.findOne(id));
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        Sensor sensor = convertToSensor(sensorDTO);

        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder exceptionMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                exceptionMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }

            throw new SensorNotCreatedException(exceptionMessage.toString());
        }

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                "Sensor with this 'id' wasn't found!",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
