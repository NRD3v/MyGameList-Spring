package com.nrd3v.mygamelist;

import com.nrd3v.mygamelist.entities.*;
import com.nrd3v.mygamelist.services.ToolService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
			session.beginTransaction();


			TestEntity test = new TestEntity();
			test.setName("Test");
			session.save(test);


			/**************************\
			 * ManyToMany test mapping *
			\**************************/
//			User user = new User("gears@war.com");
//			session.save(user);
//			Developer developer = new Developer("Epic");
//			session.save(developer);
//			Game game = new Game("Gears of War");
//			developer.addGame(game);
//			user.addGame(game);
//			session.save(game);
//			Game game = session.get(Game.class, 4);
//			System.out.println(game.getUsers());
//			User user = session.get(User.class, 4);
//			System.out.println(user.getGames());

			/*************************\
			 * OneToMany test mapping *
			\*************************/
//			Developer developer = session.get(Developer.class, 11);
//			Game game = new Game("Portal");
//			developer.addGame(game);
//			session.save(game);


			session.getTransaction().commit();
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
		System.out.println(ToolService.ANSI_RED + "STARTED AT: " + ToolService.getTime());
		SpringApplication.run(MygamelistApplication.class, args);
	}
}
