package com.nrd3v.mygamelist;

import com.nrd3v.mygamelist.core.CoreSession;
import com.nrd3v.mygamelist.entities.*;
import com.nrd3v.mygamelist.services.ToolService;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class MygamelistApplication extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}

	public static void main(String[] args) {
		CoreSession coreSession = new CoreSession();
		Session session = coreSession.getSession();
		try {
			session.beginTransaction();

			TestEntity test = new TestEntity();
			test.setName("Test");
			session.save(test);

			session.getTransaction().commit();
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			if (session.isOpen()) {
				session.close();
			}
			coreSession.getFactory().close();
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
