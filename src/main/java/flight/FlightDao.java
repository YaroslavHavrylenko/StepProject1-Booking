package flight;

import dao.DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightDao implements DAO<Flight> {
  public List<Flight> flights = new ArrayList<>();


  @Override
  public List<Flight> getAll() {
    return flights;
  }

  @Override
  public Optional<Flight> getById(int id) {
    try{
      return flights.stream().filter(flight -> id == flight.getId()).findFirst();
    }
    catch(Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public boolean delete(int id) {
    if (id > flights.size() || id < 0) return false;
    flights.removeIf(flight -> id == flight.getId());
    return true;
  }

  @Override
  public boolean save() {
    File file = new File("src/main/java/database/flight.txt");

    try {
      new BufferedReader(new FileReader(file)).lines().collect(Collectors.toList());
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));

      for (Flight flight : flights) {
        bw.write(flight.toString());
        bw.write("\n");
      }

      bw.close();
      return true;

    } catch (Exception e) {
      System.out.printf("Database file: '%s' not found! \n", file);

      try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        for (Flight flight : flights) {
          bw.write(flight.toString());
          bw.write("\n");
        }

        bw.close();
        return true;

      } catch (Exception e1) {
        return false;
      }
    }

  }

}
