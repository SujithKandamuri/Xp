package com.twair;

import com.twair.exceptions.InvalidFlightclassException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightSearchTests {
    private String source;
    private String destination;
    private Calendar departure;
    private Calendar arrival;
    private FlightSearch allFlights;
    private FlightSearch allFlights2;

    @Before
    public void setUp() throws Exception {
        source = "TestSource";
        destination = "TestDestination";
        departure = new GregorianCalendar(2016,3,10, 9, 10, 0);
        arrival = new GregorianCalendar(2016,3,10, 10, 10, 0);

        Plane plane = mock(Plane.class);
        Flight flight11 = mock(Flight.class);
        Flight flight22 = mock(Flight.class);
        Flight flight33 = mock(Flight.class);
        List<Flight> mockflightList = new ArrayList<>();
        mockflightList.add(flight11);
        mockflightList.add(flight22);
        mockflightList.add(flight33);
        allFlights2 = new FlightSearch(mockflightList);

        Plane plane1 = new Plane("type1", 10);
        Flight flight1 = new Flight("F001", source, destination, plane1, new GregorianCalendar(2016,3,10, 9, 10, 0), new GregorianCalendar(2016,3,10, 11, 10, 0));
        Flight flight2 = new Flight("F002", "TestSource1", destination, plane1, new GregorianCalendar(2016,4,10, 9, 10, 0), new GregorianCalendar(2016,4,10, 11, 10, 0));
        Flight flight3 = new Flight("F003", source, destination, plane1, new GregorianCalendar(2016,4,11, 9, 10, 0), new GregorianCalendar(2016,4,11, 11, 10, 0));
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        allFlights = new FlightSearch(flightList);

    }

    @Test
    public void shouldReturnListOfFlightsForMatchingSourceDestination() throws Exception {
        List<Flight> flights = allFlights.byLocation(source, destination).getFlightList();
        Assert.assertEquals(source, flights.get(0).getSource());
        Assert.assertEquals(destination, flights.get(0).getDestination());
        Assert.assertEquals(source, flights.get(1).getSource());
        Assert.assertEquals(destination, flights.get(1).getDestination());
        Assert.assertEquals(2, flights.size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldMandateSource() throws Exception {
        allFlights.byLocation(null, destination);
    }

    @Test(expected=IllegalArgumentException.class)
    public void sourceCannotBeEmpty() throws Exception {
        allFlights.byLocation("", destination);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldMandateDestination() throws Exception {
        allFlights.byLocation(source, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void destinationCannotBeEmpty() throws Exception {
        allFlights.byLocation(source, "");
    }

    @Test
    public void testshouldFilterByAvailableSeats() throws Exception {
        int numberOfSeats = 11;
        List<Flight> matchingFlights = allFlights.byAvailableSeats(numberOfSeats).getFlightList();
        Assert.assertEquals(0, matchingFlights.size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void numberOfSeatsCannotBeNegative() throws Exception {
        allFlights.byAvailableSeats(-10);
    }

    @Test(expected = InvalidFlightclassException.class)
    public void testShouldThrowExceptionForNullClass() throws InvalidFlightclassException{
        String flightClass = null;
        Integer noOfSeats = 10;
        List<Flight> matchingFlights = allFlights2.byFlightClass(flightClass,noOfSeats).getFlightList();
    }

    @Test
    public void testShouldReturnNumberOfFlightsBasingOnFlightClass() throws InvalidFlightclassException{
        String flightClass = "Economy";
        Integer noOfSeats = 10;

        when(allFlights2.getFlightList().get(0).getSeatsByClass(flightClass)).thenReturn(1);
        List<Flight> matchingFlights = allFlights2.byFlightClass(flightClass, noOfSeats).getFlightList();
        Assert.assertEquals(anyInt(), matchingFlights.size());
    }
}
