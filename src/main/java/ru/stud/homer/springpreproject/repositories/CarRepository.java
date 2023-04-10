package ru.stud.homer.springpreproject.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.stud.homer.springpreproject.models.Car;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(nativeQuery = true, value = "select * from Car")
    public List<Car> getAllCars();

    @Query(nativeQuery = true, value = "select * from Car limit :count")
    public List<Car> getCars(int count);

    @Query(value = "select * from Car car order by " +
            "case when :filter = 'id' then id end asc" +
            ", case when :filter = 'owner' then owner end " +
            ", case when :filter = 'brand' then brand end asc " +
            ", id " +
            "limit :count ",
            nativeQuery = true)
    public List<Car> getCars(int count, String filter);
}
