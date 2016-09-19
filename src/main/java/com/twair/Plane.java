package com.twair;

public class Plane {
    private String type;
    private Integer numberOfSeats;
    private Integer firstClassSeats;
    private Integer economyClassSeats;
    private Integer businessClassSeats;

    public Plane(String type, Integer numberOfSeats) {
        this.type = type;
        this.numberOfSeats = numberOfSeats;
    }

    public Plane(String type, Integer firsClassSeats,Integer businessClassSeats, Integer economicalClassSeats) {
        this.type = type;
        this.firstClassSeats = firsClassSeats;
        this.businessClassSeats = businessClassSeats;
        this.economyClassSeats = economicalClassSeats;
        this.numberOfSeats = firsClassSeats + businessClassSeats + economicalClassSeats;
    }

    public Integer getBusinessClassSeats() {
        return businessClassSeats;
    }

    public Integer getEconomyClassSeats() {
        return economyClassSeats;
    }

    public Integer getFirstClassSeats() {
        return firstClassSeats;
    }

    public String getType() {
        return type;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }
}
