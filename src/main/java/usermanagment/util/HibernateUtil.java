package usermanagment.util;
import usermanagment.model.User;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.Properties;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
public class HibernateUtil {
		private static SessionFactory sessionFactory;
		public static SessionFactory  getSessionFactory() {
			if(sessionFactory == null)
			{
				try {
					Configuration configuration = new Configuration();
					Properties settings = new Properties();
					settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
	                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC");
	                settings.put(Environment.USER, "root");
	                settings.put(Environment.PASS, "abc123abc");
	                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

	                settings.put(Environment.SHOW_SQL, "true");

	                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

	                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

	                configuration.setProperties(settings);
	                configuration.addAnnotatedClass(User.class);

	                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                    .applySettings(configuration.getProperties()).build();
	                System.out.println("Hibernate Java Config serviceRegistry created");
	                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	                System.out.println("Hibernate SessionFactory created successfully");
				} catch (Exception e) {
	                e.printStackTrace(); // Print the exception details
	            }
			}
			return sessionFactory;
			
			
			
		}
		
}
