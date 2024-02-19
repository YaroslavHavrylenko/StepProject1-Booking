import database.Airport;
import flight.Flight;
import flight.FlightController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightControllerTest {
  FlightController flightController;

  @BeforeEach
  void setUp() {
    flightController = new FlightController();

    Flight flightDemo = new Flight(1, Airport.BAKU.name(), "20/03/2020", 50, 0);
    Flight flightDemo2 = new Flight(2, Airport.GANJA.name(), "23/03/2020", 50, 0);
    flightController.getAll().add(flightDemo);
    flightController.getAll().add(flightDemo2);

  }


  @Test
  void getAll() {
    int expected = 2;
    int actual = flightController.getAll().size();

    assertEquals(expected, actual);

  }

  @Test
  void getByID() {
    Flight expected = flightController.getAll().get(0);
    Flight actual = flightController.getById(1);

    assertEquals(expected, actual);

  }

  @Test
  void delete() {
    boolean expected = false;
    boolean actual = flightController.delete(999);

    assertEquals(expected, actual);

  }

}