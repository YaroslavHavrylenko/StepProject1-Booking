package console;

import booking.BookingController;
import flight.FlightController;
import user.UserController;
import booking.Booking;
import user.Person;
import user.User;
import database.Airport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
public class LoginMenu {
    public static void addMainMenu() {
        StringBuilder sb = new StringBulder();
        sb.append("--------------------------------\n");
        sb.append("Менеджер по бронированию билетов\n".toUpperCase());
        sb.append("--------------------------------\n");
        sb.append("1. Рейсы\n");
        sb.append("2. Показать информацию о рейсе\n");
        sb.append("3. Найти и забронировать рейс\n");
        sb.append("4. Отменить бронирование\n");
        sb.append("5. Мои рейсы\n");
        sb.append("6. Выйти\n");
        sb.append("--------------------------------\n");
        sb.append("Предупреждение: пожалуйста, выйдите с помощью команды (6), если вы хотите выйти из приложения, чтобы сохранить свои данные в базе данных!\n");

        System.out.println(sb.toString());
        FlightController flightController = new FlightController();
        BookingController bookingController = new BookingController();
        bookingController.fillList();
        UserController userController = new UserController();
        userController.fillList();
        flightController.fillList();

        Scanner scanner = new Scanner(System.in);
        String command2 = "";

        while (!command2.equals("6")) {
            System.out.println(sb.toString());
            System.out.print("Пожалуйста, введите команду по индексу: ");
            command2 = scanner.nextLine();
            switch (command2) {

                case "1":
                    System.out.println(flightController.getFollowingFlights());
                    command2 = scanner.nextLine();
                    break;

                case "2":
                    System.out.print("Пожалуйста, введите ID рейса: ");
                    try {
                        int flightId = Integer.parseInt(scanner.nextLine());
                        System.out.println(flightController.getById(flightId).represent());
                        command2 = scanner.nextLine();
                    }   catch (Exception e) {
                        System.out.println("Пожалуйста, введите действительные данные!");
                        command2 = scanner.nextLine();
                    }
                    break;

                case "3":
                    try {
                        System.out.print("Пожалуйста, введите пункт назначения:");
                        Airport destination = Airport.valueOf(scanner.nextLine().toUpperCase());
                        System.out.print("Пожалуйста, введите дату в следующем формате «ДЕНЬ/МЕСЯЦ/ГОД» :");
                        String date = scanner.nextLine();
                        System.out.print("Пожалуйста, введите количество билетов :");
                        int tickets = Integer.parseInt(scanner.nextLine());

                        System.out.println(flightController.search(destination, date, tickets));

                        System.out.println("Хотите забронировать? (Введите ДА для бронирования, НЕТ для возврата в главное меню)");
                        command2 = scanner.nextLine();
                        if (command2.toLowerCase().equals("yes")) {
                            List<Person> passengers = new ArrayList<>();
                            System.out.print("Пожалуйста, подтвердите имя пользователя:");
                            String usernameLogin = scanner.nextLine();
                            System.out.print("Пожалуйста, подтвердите пароль:");
                            String passwordLogin = scanner.nextLine();

                            if (userController.getAll().stream().anyMatch(u -> u.getUsername().equals(usernameLogin) && u.getPassword().equals(passwordLogin))) {

                                User user = userController.getAll()
                                        .stream()
                                        .filter(u -> u.getUsername().equals(usernameLogin) && u.getPassword().equals(passwordLogin))
                                        .findFirst()
                                        .get();
                                System.out.println("Пожалуйста, введите ID вашего рейса");
                                try {
                                    int flightId = Integer.parseInt(scanner.nextLine());
                                    for (int i = 0; i < tickets; i++) {
                                        System.out.println("Введите имя пассажира:");
                                        String pName = scanner.nextLine();
                                        System.out.println("Введите фамилию пассажира:");
                                        String pSurname = scanner.nextLine();
                                        passengers.add(new Person(pName, pSurname));
                                    }
                                    bookingController.addBooking(user, flightController.getById(flightId), passengers);

                                    user.getBookings().add(new Booking(1, user, flightController.getById(flightId), passengers));

                                    flightController.getById(flightId).setReservedSeats(flightController.getById(flightId).getReservedSeats() + tickets);
                                    System.out.println("Ваш рейс успешно забронирован! Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                                    command2 = scanner.nextLine();

                                }   catch (Exception e) {
                                    System.out.println("Пожалуйста, введите действительные данные!");
                                    command2 = scanner.nextLine();
                                }

                            }else {
                                System.out.println("Пожалуйста, введите действительное имя пользователя и пароль");
                                command2 = scanner.nextLine();
                            }
                        } else if (command2.toLowerCase().equals("no")) {
                            LoginMenu.addMainMenu();
                        } else {
                            System.out.println("Вы ввели неверные данные. Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                            command2 = scanner.nextLine();
                        }

                    } catch (Exception e) {
                        System.out.println("Вы ввели неверные данные. Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                        command2 = scanner.nextLine();
                    }
                    break;

                case "4":
                    System.out.print("Пожалуйста, подтвердите имя:");
                    String usernameLogin = scanner.nextLine();
                    System.out.print("Пожалуйста, подтвердите пароль:");
                    String passwordLogin = scanner.nextLine();

                    if(userController.getAll().stream().anyMatch(u -> u.getUsername().equals(usernameLogin) && u.getPassword().equals(passwordLogin))) {

                        User user = userController.getAll()
                                .stream()
                                .filter(u -> u.getUsername().equals(usernameLogin) && u.getPassword().equals(passwordLogin))
                                .findFirst()
                                .get();

                        List<Booking> userBookings = bookingController.getAll()
                                .stream()
                                .filter(booking -> booking.getUser().getId() == user.getId())
                                .collect(Collectors.toList());
                        userBookings.forEach(ub -> System.out.println(ub.represent()));

                        System.out.print("Пожалуйста, выберите ID рейса, который вы хотите отменить: ");

                        try {
                            int bookingID = Integer.parseInt(scanner.nextLine());
                            int flightID = bookingController.getById(bookingID).getFlight().getId();

                            flightController.getById(flightID).setReservedSeats(flightController.getById(flightID).getReservedSeats() -
                                    bookingController.getById(bookingID).getPassengers().size());
                            bookingController.delete(bookingID);

                            System.out.println("Бронирование успешно отменено. Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                            command2 = scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println("Вы ввели неверные данные. Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                            command2 = scanner.nextLine();
                        }
                    } else {
                        System.out.println("Пожалуйста, введите действительное имя пользователя и пароль");
                        command2 = scanner.nextLine();
                    }
                    break;

                case "5":
                    System.out.print("Пожалуйста, подтвердите имя:");
                    String usernameLoginMF = scanner.nextLine();
                    System.out.print("Пожалуйста, подтвердите пароль:");
                    String passwordLoginMF = scanner.nextLine();

                    System.out.println(bookingController.getAll());

                    if (userController.getAll().stream().anyMatch(u -> u.getUsername().equals(usernameLoginMF) && u.getPassword().equals(passwordLoginMF))) {

                        User user = userController.getAll()
                                .stream()
                                .filter(u -> u.getUsername().equals(usernameLoginMF) && u.getPassword().equals(passwordLoginMF))
                                .findFirst()
                                .get();

                        System.out.println("Ваши рейсы:");
                        List<Booking> userBookings = bookingController.getAll()
                                .stream()
                                .filter(b -> b.getUser().getId() == user.getId())
                                .collect(Collectors.toList());
                        if (userBookings.size() == 0) System.out.println("Вы еще не забронировали ни одного рейса");
                        userBookings.forEach(ub -> System.out.println(ub.represent()));

                        System.out.println("Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                        command2 = scanner.nextLine();

                    } else {
                        System.out.println("Пожалуйста, введите действительное имя пользователя и пароль");
                        command2 = scanner.nextLine();
                    }
                    break;

                case "6":
                    System.out.println("Уважаемый пользователь, спасибо, что выбрали нас. Мы надеемся, что вам понравится ваше путешествие.");
                    bookingController.save();
                    userController.save();
                    flightController.save();
                    break;

                default:
                    System.out.println("Вы ввели неверные данные. Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                    command2 = scanner.nextLine();
                    break;

            }
        }
    }
}
