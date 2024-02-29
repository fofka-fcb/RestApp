package ru.myPackage.restApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myPackage.restApp.models.Sensor;
import ru.myPackage.restApp.repositories.SensorRepository;
import ru.myPackage.restApp.utils.SensorNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Sensor findOne(int id) {
        return sensorRepository.findById(id).orElseThrow(SensorNotFoundException::new);
    }

    public Optional<Sensor> findByName(String name) {
        Sensor sensor = sensorRepository.findSensorByName(name);

        return Optional.ofNullable(sensor);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensor.setCreated(new Date());

        sensorRepository.save(sensor);
    }

}
