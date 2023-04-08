package ru.stud.homer.springpreproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stud.homer.springpreproject.models.Car;
import ru.stud.homer.springpreproject.repositories.CarRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarService {
    private final CarRepository carRepository;

    @Value("${maxCar}")
    private Integer maxCar;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional(readOnly = false)
    public void addCar(String brand, String owner) {
        Car car = new Car(brand, owner);
        carRepository.save(car);
    }

    public List<Car> findCars(Integer count) {
        if (count == null || count >= maxCar) {
            return carRepository.findAll();
        } else {
            return carRepository.findAll().subList(0, count);
        }
    }
}
