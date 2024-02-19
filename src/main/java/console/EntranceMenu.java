package console;

import flight.FlightController;
import user.UserController;
import user.User;
public class EntranceMenu {
    public static void addEntranceMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------\n");
        sb.append("Менеджер по бронированию билетов\n");
        sb.append("--------------------------------\n");
        sb.append("Войти\n");
        sb.append("Регистрация\n");
        sb.append("Войти как гость\n");
        sb.append("Помощь\n");
        sb.append("Выйти\n");
        sb.append("--------------------------------\n");

        UserController userController = new UserController();
        FlightController flightController = new FlightController();
        flightController.generateFlight();
        userController.fillList();

        Scanner scanner = new Scanner(System.in);
        String command = "";
        while(!command.equals("5")) {
            System.out.println(sb.toString());
            System.out.print("Пожалуйста, введите комманду по индексу: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Пожалуйста, введите имя пользователя:");
                    String usernameLogin = scanner.nextLine();
                    System.out.print("Пожалуйста, введите пароль:");
                    String passwordLogin = scanner.nextLine();
                    User loggedUser = new User(usernameLogin, passwordLogin);

                    if (userController.getAll().stream().anyMatch(user -> user.getUsername().equals(usernameLogin) && user.getPassword().equals(passwordLogin))) {
                        System.out.println("Вход...");
                        LoginMenu.addMainMenu();
                        command = "5";
                        break;
                    }   else {
                        System.out.println("Имя пользователя или пароль неверны. Пожалуйста, нажмите Enter, чтобы вернуться в главное меню и повторите попытку.");
                        command = scanner.nextLine();
                    }
                    break;

                case "2":
                    System.out.print("Пожалуйста, введите имя пользователя:");
                    String username = scanner.nextLine();
                    System.out.print("Пожалуйста, введите пароль:");
                    String password = scanner.nextLine();
                    userController.register(username, password);
                    userController.save();
                    System.out.println("Вы успешно зарегистрировались. Пожалуйста, нажмите Enter, чтобы вернуться в главное меню:");
                    command = scanner.nextLine();
                    break;

                case "3":
                    GuestMenu.addMainMenu();
                    command = "5";
                    break;

                case "4";
                    System.out.println("К сожалению, мы не можем вам помочь ;'( \n Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                    command = scanner.nextLine();
                    break;

                case "5";
                    System.out.println("До свидания");
                    userController.save();
                    break;

                default:
                    System.out.println("Вы ввели неверные данные. Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                    command = scanner.nextLine();
                    break;
            }
        }
    }
}
