package booking;

import user.Person;
import user.User;
import flight.Flight;

import java.util.List;

public class Booking {
    private int id;
    private User user;
    private Flight flight;
    private List<Person> passengers;

    public Booking(int id, User user, Flight flight) {
        this.id = id;
        this.user = user;
        this.flight = flight;
    }

    public Booking(int id, User user, Flight flight, List<Person> passengers) {
        this.id = id;
        this.user = user;
        this.flight = flight;
        this.passengers = passengers;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Flight getFlight() {
        return flight;
    }

    public List<Person> getPassengers() {
        return passengers;
    }

    public String represent() {
        return String.format("BOOKING ID: %d USER: '%s' FLIGHT: '%s' PASSENGERS: '%s' \n", id,
                user.represent(), flight.represent(), passengers.toString());
    }

    @Override
    public String toString() {
        return String.format("%d;%s;%s;%s;", id, user, flight, passengers.toString());
    }
}
