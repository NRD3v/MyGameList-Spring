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


		try {
			System.out.println("START");

			/*************************\
			 * OneToMany test mapping *
			\*************************/
//			session.beginTransaction();
//			Developer developer = session.get(Developer.class, 11);
//			Game game = new Game("Portal");
//			developer.add(game);
//			session.save(game);
//			session.getTransaction().commit();
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			if (session.isOpen()) {
				session.close();
			}
			factory.close();
		}

		/******************\
		 * Connection test *
		\******************/
		String jdbcUrl = "jdbc:mysql://localhost:3306/mygamelist?useSSL=false";
		String jdbcUser = "root";
		String jdbcPass = "root";
		try {
			System.out.println("Connecting to DB...");
			Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
			System.out.println(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}


		/*************\
		 * App launch *
		\*************/
		SpringApplication.run(MygamelistApplication.class, args);
	}
}
