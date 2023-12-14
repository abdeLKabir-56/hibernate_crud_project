package usermanagment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mysql.cj.util.StringUtils;

import usermanagment.util.HibernateUtil;
import usermanagment.model.User;
public class UserDao {
	public void saveUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
	public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
	public void deleteUser(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("user is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	public User getUser(int id) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = session.get(User.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }
	
    @SuppressWarnings("unchecked")
	public List < User > getAllUser() {

        Transaction transaction = null;
        List < User > listOfUser = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
            System.out.println("SessionFactory obtained successfully");
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            listOfUser = session.createQuery("from User").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return listOfUser;
    }
    public List<User> searchUser(String search) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("SessionFactory obtained successfully");
            Transaction transaction = null;
            List<User> listOfUser = null;
            if (StringUtils.isNullOrEmpty(search)) {
                // Return all users if no search parameter provided
                return session.createQuery("from User", User.class).getResultList();
            }
            // Build dynamic query based on search parameter
            String queryString = "from User u where u.name like :search or u.email like :search";
            Query query = session.createQuery(queryString);
            query.setParameter("search", "%" + search + "%");
            return query.getResultList();
        
    }
    }
    public int countUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("SessionFactory obtained successfully");
            Transaction transaction = null;
            List<User> listOfUser = null;
            
            // Build dynamic query based on search parameter
            String queryString = "select count(*) from User";
            Query query = session.createQuery(queryString);
            
            Integer count = (int) ((Long) query.uniqueResult()).longValue();
            
             return count;
        
    }
    }
}

