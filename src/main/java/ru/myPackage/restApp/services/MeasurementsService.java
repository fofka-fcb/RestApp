package ru.myPackage.restApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myPackage.restApp.models.Measurements;
import ru.myPackage.restApp.models.Sensor;
import ru.myPackage.restApp.repositories.MeasurementsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementRepository;

    public List<Measurements> findAllByOwner(Sensor sensor) {
        return measurementRepository.findAllByOwner(sensor);
    }

    public List<Measurements> findAllByOwner(Sensor sensor, Boolean b) {
        return measurementRepository.findAllByOwnerAndRaining(sensor, b);
    }


}
