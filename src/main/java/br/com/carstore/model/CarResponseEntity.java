package br.com.carstore.model;

import br.com.carstore.dto.CarDTO;

import java.util.List;

public class CarResponseEntity {

    private List<CarDTO> cars;

    public CarResponseEntity() {
    }

    public CarResponseEntity(List<CarDTO> cars){
        this.cars = cars;
    }



}
