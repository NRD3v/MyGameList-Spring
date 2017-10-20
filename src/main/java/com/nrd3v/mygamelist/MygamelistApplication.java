package com.nrd3v.mygamelist;

import com.nrd3v.mygamelist.entities.Developer;
import com.nrd3v.mygamelist.entities.Game;
import com.nrd3v.mygamelist.entities.User;
import com.nrd3v.mygamelist.entities.UserAggregate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class MygamelistApplication {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(Developer.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(UserAggregate.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();


		/**********************************************************\
		 * Exercise 6: Test OneToMany mapping (creating & reading) *
		\**********************************************************/
//		try {
//			session.beginTransaction();
//			Developer developer = session.get(Developer.class, 11);
//			System.out.println(developer);
//			System.out.println(developer.getGames());
//			Game game = session.get(Game.class, 1);
//			System.out.println(game);
//			System.out.println(game.getDeveloper());
//			session.getTransaction().commit();
//		}
//		catch (Exception e) {
//			e.getStackTrace();
//		}
//		finally {
//			if (session.isOpen()) {
//				session.close();
//			}
//			factory.close();
//		}

//		try {
//			session.beginTransaction();
//			Developer developer = session.get(Developer.class, 11);
//			Game game = new Game("Portal");
//			developer.add(game);
//			session.save(game);
//			session.getTransaction().commit();
//		}
//		catch (Exception e) {
//			e.getStackTrace();
//		}
//		finally {
//			if (session.isOpen()) {
//				session.close();
//			}
//			factory.close();
//		}


		/*********************************************\
		 * Exercise 5: Delete OneToOne mapped objects *
		\*********************************************/
//		try {
//			int userId = 2;
//			int userAggregateId = 4;
//			session.beginTransaction();
//			User user = session.get(User.class, userId);
//			UserAggregate userAggregate = session.get(UserAggregate.class, userAggregateId);
//			if (user != null && userAggregate != null) {
//				// Object relation reference break
//				userAggregate.getUser().setUserAggregate(null);
//				session.delete(user);
//			}
//			session.getTransaction().commit();
//		}
//		catch (Exception e) {
//			e.getStackTrace();
//		}
//		finally {
//			if (session.isOpen()) {
//				session.close();
//			}
//			factory.close();
//		}


		/******************************************\
		 * Exercise 4: Get OneToOne mapped objects *
		\******************************************/
//		try {
//			int userId = 2;
//			int userAggregateId = 4;
//			session.beginTransaction();
//			User user = session.get(User.class, userId);
//			UserAggregate userAggregate = session.get(UserAggregate.class, userAggregateId);
//			if (user != null && userAggregate != null) {
//				System.out.println(user.getEmail() + " " + user.getUserAggregate().getName());
//				System.out.println(userAggregate.getName() + " " + userAggregate.getUser().getEmail());
//			}
//			session.getTransaction().commit();
//		}
//		catch (Exception e) {
//			e.getStackTrace();
//		}
//		finally {
//			if (session.isOpen()) {
//				session.close();
//			}
//			factory.close();
//		}


		/***********************************************************************\
		* Exercise 3: Test the User & UserAggregate OneToOne entity persistence *
		\***********************************************************************/
//		try {
//			User user = new User("test@test.com");
//			UserAggregate userAggregate = new UserAggregate("Test Nom");
//			user.setUserAggregate(userAggregate);
//			session.beginTransaction();
//			session.save(user);
//			session.getTransaction().commit();
//		}
//		catch (Exception e) {
//			e.getStackTrace();
//		}
//		finally {
//			if (session.isOpen()) {
//				session.close();
//			}
//			factory.close();
//		}

		/******************************\
		 * Exercise 1: Test connection *
		\******************************/
//		String jdbcUrl = "jdbc:mysql://localhost:3306/mygamelist?useSSL=false";
//		String jdbcUser = "root";
//		String jdbcPass = "root";
//		try {
//			System.out.println("Connecting to DB...");
//			Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
//			System.out.println(connection);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}


		/*************\
		 * App launch *
		\*************/
		SpringApplication.run(MygamelistApplication.class, args);
	}
}
