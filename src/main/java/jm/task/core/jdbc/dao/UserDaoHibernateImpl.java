package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Database has not been created");
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users")
                    .addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Database has not been dropped");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Database has not been increased");
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Database has not been decreased");
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            users = session.createNativeQuery("SELECT * FROM users", User.class)
                    .getResultList();
            
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Database has not been read");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Database has not been cleaned");
        }
    }
}
