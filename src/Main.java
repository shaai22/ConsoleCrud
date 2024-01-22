import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Модуль регистрации
        List USERS = new ArrayList<String>();
        System.out.println("Регистрация первого пользователя\n");
        System.out.println("Введите логин:");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Введите пароль:");
        String password = in.nextLine();

        User creatingUser = new User();
        creatingUser.setId(new Random().nextLong(100L));
        creatingUser.setName(name);
        creatingUser.setPassword(password);
        USERS.add(creatingUser);

        // Модуль авторизации
        Boolean NOT_AUTH = true;
        while (NOT_AUTH) {
            System.out.println("Пожалуйста, авторизуйтесь\n");
            System.out.println("Ваш логин:");
            in = new Scanner(System.in);
            name = in.nextLine();
            System.out.println("Ваш пароль:");
            password = in.nextLine();

            for (int x = 0; x < USERS.size(); x++) {
                User searchUser = (User) USERS.get(x);
                Boolean isNeedUser = searchUser.getName().equals(name);
                if (isNeedUser) {
                    Boolean isNeedUserPassword = searchUser.getPassword().equals(password);
                    if (isNeedUserPassword) {
                        NOT_AUTH = false;
                        System.out.println("Вы авторизованы");
                    } else {
                        System.out.println("Неверный логин или пароль");
                        continue;
                    }
                }
            }
        }
    }
}
