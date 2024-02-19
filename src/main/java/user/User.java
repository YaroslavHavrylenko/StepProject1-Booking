package user;

import booking.Booking;

import java.util.ArrayList;
import java.util.List;

public class User {
  private int id;
  private String username;
  private String password;
  private List<Booking> bookings = new ArrayList<>();

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(int id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public User(int id, String username, String password, List<Booking> bookings) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.bookings = bookings;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public String represent() {
    return String.format("USER: '%s' \n", getUsername());
  }

  @Override
  public String toString() {
    return String.format("%d %s %s", getId(), getUsername(), getPassword());
  }

}
