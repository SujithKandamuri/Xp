package com.twair;

import com.twair.exceptions.InvalidFlightclassException;

import java.util.Calendar;

public class Flight {
    private String source;
    private String destination;
    private Plane plane;
    private String number;
    private final Integer availableSeats;
    private Calendar departureTime;
    private Calendar arrivalTime;
    private Integer economyClassSeats;
    private Integer businessClassSeats;
    private Integer firstClassSeats;

    public Flight(String number, String source, String destination, Plane plane, Calendar departure, Calendar arrival) throws Exception {
        this.source = source;
        this.destination = destination;
        this.plane = plane;
        this.number = number;
        this.availableSeats = plane.getNumberOfSeats();
        this.economyClassSeats = plane.getEconomyClassSeats();
        this.businessClassSeats = plane.getBusinessClassSeats();
        this.firstClassSeats = plane.getFirstClassSeats();
        setScheduleTime(departure, arrival);
    }

    public Integer getEconomyClassSeats() {
        return economyClassSeats;
    }

    public Integer getBusinessClassSeats() {
        return businessClassSeats;
    }

    public Integer getFirstClassSeats() {
        return firstClassSeats;
    }

    public boolean canBook(Integer numberOfSeats) {
        return this.availableSeats - numberOfSeats >= 0;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getNumber() {
        return number;
    }

    public Calendar getDepartureTime() {
        return departureTime;
    }

    public Calendar getArrivalTime() {
        return arrivalTime;
    }

    private void setScheduleTime(Calendar departureTime, Calendar arrivalTime) throws Exception {
        if(departureTime.after(arrivalTime)) {
            throw new Exception("departure time cannot be greater than arrival time");
        }
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Integer getSeatsByClass(String flightClass) {

        flightClass = flightClass.toLowerCase();
        switch(flightClass)
        {
            case "economyclass":
                return getEconomyClassSeats();

            case "businessclass":
                return getBusinessClassSeats();

            case "firstclass":
                return getFirstClassSeats();

            default:
                return 0;

        }
    }
}
