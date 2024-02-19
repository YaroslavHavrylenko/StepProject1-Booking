package flightGenerator;

import database.Airport;
import flight.Flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class FlightGenerator {

  public static ArrayList<Flight> generateFlight(int count) {
    ArrayList<Flight> generatedFlight = new ArrayList<>();
    Random rndm = new Random();
    ArrayList<Airport> randomDestinations = new ArrayList<>();
    EnumSet.allOf(Airport.class).forEach(air -> randomDestinations.add(air));

    int seats = 50;

    for (int i = 0; i < count; i++) {
      int randomDestIndex = rndm.nextInt(randomDestinations.size() - 1);
      int randomDate = rndm.nextInt(15);
      int randomMinute = rndm.nextInt(150);
      String randomDest = randomDestinations.get(randomDestIndex).name();

      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd|HH:mm");
      LocalDateTime localDateTime = LocalDateTime.now();
      LocalDateTime flightDate = localDateTime
              .plusDays(randomDate)
              .plusHours(randomDate)
              .plusMinutes(randomMinute);

      String formattedFlightDate = flightDate.format(dateTimeFormatter);

      generatedFlight.add(new Flight(i + 1, randomDest, formattedFlightDate, seats, 0));
    }
    return generatedFlight;
  }

}
