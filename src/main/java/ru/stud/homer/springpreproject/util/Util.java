package ru.stud.homer.springpreproject.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "util")
public class Util {
    private int maxCar;
    private List<String> filters;

    public int getMaxCount() {
        return maxCar;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setMaxCar(int maxCar) {
        this.maxCar = maxCar;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }
}
