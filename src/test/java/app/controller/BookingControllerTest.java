package app.controller;

import booking.Booking;
import booking.BookingController;
import flight.Flight;
import user.Person;
import user.User;
import database.Airport;
import flight.FlightController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.UserController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingControllerTest {
  FlightController flightController;
  UserController userController;
  BookingController bookingController;


  @BeforeEach
  void setUp() {
    flightController = new FlightController();
    userController = new UserController();
    bookingController = new BookingController();

    User emin = new User(1, "Emin", "12345" );
    User shafa = new User(2, "Shafa", "12345" );
    userController.getAll().add(emin);
    userController.getAll().add(shafa);

    Flight flightDemo = new Flight(1, Airport.BAKU.name(), "20/03/2020", 50, 0);
    Flight flightDemo2 = new Flight(2, Airport.GANJA.name(), "23/03/2020", 50, 0);
    flightController.getAll().add(flightDemo);
    flightController.getAll().add(flightDemo2);

    Booking bookingDemo = new Booking(1, emin, flightDemo);
    Booking bookingDemo2 = new Booking(2, shafa, flightDemo2);
    bookingController.getAll().add(bookingDemo);
    bookingController.getAll().add(bookingDemo2);
  }


  @Test
  void getAll() {
    int expected = 2;
    int actual = bookingController.getAll().size();

    assertEquals(expected, actual);
  }

  @Test
  void getByID() {
    Booking expected = bookingController.getAll().get(0);
    Booking actual = bookingController.getById(1);

    assertEquals(expected, actual);
  }

  @Test
  void delete() {

    boolean expected = false;
    boolean actual = bookingController.delete(9);

    assertEquals(expected, actual);

  }

  @Test
  void addBooking() {
    int initialSize = bookingController.getAll().size();

    User shafa3 = new User(3, "Shafa1", "12345" );
    Flight flightDemo3 = new Flight(2, Airport.GANJA.name(), "25/03/2020", 50, 0);
    List<Person> passengers = new ArrayList<>();
    passengers.add(new Person("emin", "israfilzadeh"));
    bookingController.addBooking(shafa3, flightDemo3, passengers);

    int finalSize = bookingController.getAll().size();

    boolean expected = true;
    boolean actual = initialSize<finalSize;

    assertEquals(expected, actual);
  }

}