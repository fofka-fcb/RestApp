package ru.myPackage.restApp.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.myPackage.restApp.models.Sensor;
import ru.myPackage.restApp.services.SensorService;

@Component
@RequiredArgsConstructor
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensorService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}
