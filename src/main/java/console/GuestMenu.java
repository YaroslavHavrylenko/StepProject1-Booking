package console;

import flight.FlightController;

import java.util.Scanner;
public class GuestMenu {
    public static void addMainMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------\n");
        sb.append("Менеджер по бронированию билетов\nВы вошли как гость. Пожалуйста, зарегистрируйтесь, чтобы получить полный доступ. \n".toUpperCase());
        sb.append("--------------------------------\n");
        sb.append("1. Рейсы\n");
        sb.append("2. Показать информацию о рейсе\n");
        sb.append("3. Вернуться в главное меню\n");
        sb.append("4. Выйти\n");
        sb.append("--------------------------------\n");

        System.out.println(sb.toString());
        FlightController flightController = new FlightController();
        flightController.fillList();

        Scanner scanner = new Scanner(System.in);
        String command1 = "";

        while (!command1.equals("4")) {
            System.out.println(sb.toString());
            System.out.print("Пожалуйста, введите комманду по индексу: ");
            command1 = scanner.nextLine();
            switch (command1) {

                case "1":
                    System.out.println(flightController.getFollowingFlights());
                    System.out.println("Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                    command1 = scanner.nextLine();
                    break;

                case "2":
                    System.out.print("Пожалуйста, введите ID рейса: ");
                    try {
                        int flightId = Integer.parseInt(scanner.nextLine());
                        System.out.println(flightController.getById(flightId).represent());
                        System.out.println("Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                        command1 = scanner.nextLine();
                    }   catch (Exception e) {
                        System.out.println("Вы ввели неверную команду. Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                        command1 = scanner.nextLine();
                    }
                    break;

                case "3":
                    System.out.println("Возврат в главное меню...");
                    EntranceMenu.addEntranceMenu();
                    command1 = "4";
                    break;

                default:
                    System.out.println("Вы ввели неверныe данные. Пожалуйста, нажмите Enter, чтобы вернуться в главное меню");
                    command1 = scanner.nextLine();
                    break;
            }
        }
    }
}
