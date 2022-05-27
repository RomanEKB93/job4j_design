package ru.job4j.map;

import java.util.Arrays;
import java.util.Objects;

public class Car {
    private boolean isLeftHand;

    private short releaseYear;

    private int mileage;

    private float petrolConsumption;

    private String model;

    private String[] owners;

    private double price;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return isLeftHand == car.isLeftHand
                && releaseYear == car.releaseYear
                && mileage == car.mileage
                && Float.compare(car.petrolConsumption, petrolConsumption) == 0
                && Double.compare(car.price, price) == 0
                && Objects.equals(model, car.model)
                && Arrays.equals(owners, car.owners);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int separateHk = isLeftHand ? 1 : 0;
        result = 31 * result + separateHk;
        result = 31 * result + releaseYear;
        result = 31 * result + mileage;
        separateHk = Float.floatToIntBits(petrolConsumption);
        result = 31 * result + separateHk;
        separateHk = model == null ? 0 : model.hashCode();
        result = 31 * result + separateHk;
        separateHk = 0;
        if (owners != null) {
            separateHk = 1;
            for (String el : owners) {
                separateHk = 31 * separateHk + el.hashCode();
            }
        }
        result = 31 * result + separateHk;
        long temp = Double.doubleToLongBits(price);
        separateHk = (int) (temp ^ (temp >>> 32));
        result = 31 * result + separateHk;
        return result;
    }
}
