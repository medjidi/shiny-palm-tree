package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();

        service.saveUser("Oleg", "Myasnitskiy", (byte) 24);
        service.saveUser("Astlan", "Martin", (byte) 99);
        service.saveUser("Shanson", "Klassicheskiy", (byte) 115);
        service.saveUser("Majid", "Magomedov", (byte) 18);

        List<User> users = service.getAllUsers();
        users.forEach(System.out::println);
        service.cleanUsersTable();
        service.dropUsersTable();

    }

}