import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Hall[] halls = {new Hall(10, 20, 30), new Hall(20, 20, 20)};
        Seance[] seances = {new Seance(1, "Фильм 1", "18:00", 1), new Seance(2, "Фильм 2", "18:00", 2)};
        Cinema cinema = new Cinema(halls, seances);

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1. Поиск фильма");
            System.out.println("2. Бронирование");
            System.out.println("3. Выход");
            int command = Integer.parseInt(in.nextLine());
            switch (command) {
                case 1:
                    System.out.println("Введите название фильма");
                    String name = in.nextLine();
                    ArrayList<Seance> found = cinema.find(name);
                    if (found.isEmpty()) {
                        System.out.println("Фильм не найден");
                    } else {
                        for (Seance seance : found) {
                            System.out.println(seance);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Введите номер сеанса");
                    int id = Integer.parseInt(in.nextLine());
                    Seance seance = cinema.findById(id);

                    if (seance == null) {
                        System.out.println("Сеанс не найден");
                    } else {
                        System.out.println("Введите номер ряда");
                        int row = Integer.parseInt(in.nextLine());
                        System.out.println("Введите номер места");
                        int seat = Integer.parseInt(in.nextLine());
                        int hallNumber = seance.getHallNumber();
                        Hall hall = halls[hallNumber - 1];

                        if (((row >= 1) && (row <= hall.getSeats().length))) {
                            int rowSeats = hall.getSeats()[row - 1];

                            if ((seat>=1)&&(seat <= rowSeats)){
                                boolean p = cinema.reserve(seance, row, seat);
                                if (p == true){
                                    System.out.println("Успешно забронированно");
                                }  else if (p == false) {
                                    System.out.println("Место занято");
                                }

                            } else {
                                System.out.println("Неправильно задано значение места");
                            }

                        } else {
                            System.out.println("Неправильно заданы значения ряда");
                        }
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Введите номер от 1 до 3");
                    break;
            }
        }
    }
}