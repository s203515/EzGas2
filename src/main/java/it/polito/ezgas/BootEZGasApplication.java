package it.polito.ezgas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

@SpringBootApplication
public class BootEZGasApplication {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Application entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BootEZGasApplication.class, args);
	}
	
	/**
	 * Setup the application database at startup
	 * 
	 * @throws SQLException
	 */
	@PostConstruct
	public void setupDbWithData() throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		conn.close();

		// get the list of users stored in the database
		List<User> users = userRepository.findAll();

		// check if an administrator user is available
		User admin = null;
		for (User current : users) {
			if (current.getAdmin()) {
				admin = current;
				break;
			}
		}
		
		// there is no administrator user
		if (admin == null) {
			// create the administrator user
			User user= new User("admin", "admin", "admin@ezgas.com", 5);
			user.setAdmin(true);
			
			// save it in the database
			userRepository.save(user);
		}
	}

}
