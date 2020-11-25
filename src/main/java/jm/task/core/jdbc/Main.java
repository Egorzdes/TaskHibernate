package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();


        userService.saveUser("egor", "zdes", (byte) 27);
        System.out.println("User с именем – egor добавлен в базу данных");
        userService.saveUser("Igor", "smirnov", (byte) 25);
        System.out.println("User с именем – Igor добавлен в базу данных");
        userService.saveUser("Viktor", "Ivanov", (byte) 30);
        System.out.println("User с именем – Viktor добавлен в базу данных");
        userService.saveUser("Sasha", "Shustov", (byte) 25);
        System.out.println("User с именем – Sasha добавлен в базу данных");


        List<User> userList = userService.getAllUsers();
        for (User user : userList) System.out.println(user);


        userService.cleanUsersTable();


        userService.dropUsersTable();


        UserDao userDao = new UserDaoHibernateImpl();


        userDao.createUsersTable();


        userDao.saveUser("egor", "zdes", (byte) 27);
        userDao.saveUser("Igor", "smirnov", (byte) 25);
        userDao.saveUser("Viktor", "Ivanov", (byte) 30);
        userDao.saveUser("Sasha", "Shustov", (byte) 25);


        userDao.getAllUsers();


        userDao.cleanUsersTable();


        userDao.dropUsersTable();
    }
}
