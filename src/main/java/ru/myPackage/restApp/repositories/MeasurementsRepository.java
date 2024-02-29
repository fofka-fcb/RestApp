package ru.myPackage.restApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.myPackage.restApp.models.Measurements;
import ru.myPackage.restApp.models.Sensor;

import java.util.List;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {

    List<Measurements> findAllByOwner(Sensor sensor);

    List<Measurements> findAllByOwnerAndRaining(Sensor sensor, Boolean b);

}
