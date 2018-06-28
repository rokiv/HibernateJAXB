package hibernate;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO {
    public User findUserById(int id) {
        return HibernateSessionFactory.getSession().get(User.class,id);
    }

    public void saveUser(User user) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tz1 = session.beginTransaction();
        session.save(user);
        tz1.commit();
        session.close();
    }

    public List<User> getAllUsers() {
        return (List<User>) HibernateSessionFactory.getSession().
                createQuery("from User").list();
    }

    public void saveAllUsers(List<User> users) {
        for (User user: users
             ) {
            HibernateSessionFactory.getSession().merge(user);
        }
    }
}
