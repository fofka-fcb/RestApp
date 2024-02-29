package ru.myPackage.restApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.myPackage.restApp.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Sensor findSensorByName(String name);
}
