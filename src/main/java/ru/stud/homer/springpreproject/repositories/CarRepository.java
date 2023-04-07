package ru.stud.homer.springpreproject.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.stud.homer.springpreproject.models.Car;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    @Override
    List<Car> findAll();
}
