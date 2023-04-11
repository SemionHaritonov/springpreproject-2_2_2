package ru.stud.homer.springpreproject.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stud.homer.springpreproject.models.Car;
import ru.stud.homer.springpreproject.repositories.CarRepository;
import ru.stud.homer.springpreproject.exceptions.CarBadFilterException;
import ru.stud.homer.springpreproject.util.CarProperties;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarProperties carProperties;

    @Autowired
    public CarService(CarRepository carRepository, CarProperties carProperties) {
        this.carRepository = carRepository;
        this.carProperties = carProperties;
    }

    @Transactional()
    public void addCar(String brand, String owner) {
        Car car = new Car(brand, owner);
        carRepository.save(car);
    }

    public List<Car> findCars(Integer count) {
        if (count == null || count > carProperties.getMaxCount()) {
            return carRepository.getAllCars();
        } else {
            return carRepository.getCars(count);
        }

    }

    public List<Car> findCars(Integer count, String filter) {
        if(count != null && filter != null && carProperties.getFilters().contains(filter.toUpperCase())) {
            return carRepository.getCars(count, filter.toLowerCase());
        } else if (count == null && filter == null) {
            return carRepository.getAllCars();
        } else {
            Optional<List<Car>> optional = Optional.empty();
            return optional.orElseThrow(CarBadFilterException::new);
        }
    }

    @PostConstruct
    @Transactional()
    public void init() {
        carRepository.save(new Car("Vaz", "Tolya"));
        carRepository.save(new Car("Mercedes", "Tolya"));
        carRepository.save(new Car("Rolls-Royce", "Tolya"));
        carRepository.save(new Car("Vaz", "Roma"));
        carRepository.save(new Car("Mercedes", "Roma"));
        carRepository.save(new Car("Rolls-Royce", "Roma"));
        carRepository.save(new Car("Vaz", "Vasya"));
        carRepository.save(new Car("Mercedes", "Vasya"));
        carRepository.save(new Car("Rolls-Royce", "Vasya"));
        carRepository.save(new Car("Porsche", "Masha"));
    }
}
