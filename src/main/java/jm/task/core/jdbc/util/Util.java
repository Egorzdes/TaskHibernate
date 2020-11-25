package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private Connection connection;
    private Session session;
    private SessionFactory sessionFactory;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=Europe/Moscow";
    private static final String LOGIN = "egorzdes";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Configuration Configuration() {

        Properties properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.setProperty(Environment.USER, "egorzdes");
        properties.setProperty(Environment.PASS, "1234");
        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC");


        Configuration configuration = new Configuration();
        configuration.setProperties(properties);

        return configuration.addAnnotatedClass(User.class);
    }
    public Session getSession(){
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.username", LOGIN);
        configuration.setProperty("hibernate.connection.password", PASSWORD);
        configuration.setProperty("hibernate.connection.url", HOST);
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();
        return session = sessionFactory.openSession();
    }
    public void closeSessionFactory() {
        sessionFactory.close();
    }
}



