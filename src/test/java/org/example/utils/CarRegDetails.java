package org.example.utils;

public class CarRegDetails {

    private final String regNo;
    private final String make;
    private final String model;
    private final String year;

    public CarRegDetails(String regNo, String make, String model, String year) {
        this.regNo = regNo;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "CarRegDetails{" +
                "regNo='" + regNo + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
