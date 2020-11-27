package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Util util = new Util();

        try (SessionFactory factory = util.Configuration().buildSessionFactory();
             Session session = factory.openSession()) {
            System.out.println("Таблица user создана");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Util util = new Util();

        try (SessionFactory factory = util.Configuration().buildSessionFactory();
             Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createNativeQuery("DROP TABLE mydbtest.users;");
            deleteQuery.executeUpdate();
            System.out.println("таблица User удалена");
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();

        try (SessionFactory factory = util.Configuration().buildSessionFactory();
             Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        tr.commit();
        session.close();
    }


    @Override
    public List<User> getAllUsers() {
        Util util = new Util();
        List<User> userList = new ArrayList<>();
        try (SessionFactory factory = util.Configuration().buildSessionFactory();
             Session session = factory.openSession()) {
            List<Object[]> users = session.createNativeQuery("SELECT * FROM users;").list();
            for (Object[] objects : users) {
                User user1 = new User((String) objects[3], (String) objects[2], (byte) objects[1]);
                userList.add(user1);
                System.out.println(user1.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }


    @Override
    public void cleanUsersTable() {
        Util util = new Util();
        try (SessionFactory factory = util.Configuration().buildSessionFactory();
             Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createNativeQuery("TRUNCATE TABLE users;");
            deleteQuery.executeUpdate();
            System.out.println("таблица user очищена");
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}