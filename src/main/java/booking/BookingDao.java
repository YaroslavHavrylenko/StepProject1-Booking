package booking;

import dao.DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingDao implements DAO<Booking> {
    public List<Booking> bookings = new ArrayList<>();


    @Override
    public List<Booking> getAll() {
        return bookings;
    }

    @Override
    public Optional<Booking> getById(int id) {
        try{
            return bookings.stream().filter(booking -> id == booking.getId()).findFirst();
        }
        catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(int id) {
        if (id > bookings.size() || id < 0) return false;
        bookings.removeIf(booking -> id == booking.getId());
        return true;
    }

    @Override
    public boolean save() {
        File file = new File("src/main/java/database/booking.txt");

        try {
            new BufferedReader(new FileReader(file)).lines().collect(Collectors.toList());
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (Booking booking : bookings) {
                bw.write(booking.toString());
                bw.write("\n");
            }

            bw.close();
            return true;

        } catch (Exception e) {

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));

                for (Booking booking : bookings) {
                    bw.write(booking.toString());
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
