package com.utez.geco;

import com.utez.geco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class GecoRestServicesApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(GecoRestServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createProcedure();
	}

	public void createProcedure() {
		String sql = "CREATE PROCEDURE registerUser(" +
				"    IN Uname VARCHAR(100)," +
				"    IN Usurname VARCHAR(100)," +
				"    IN Ulastname VARCHAR(100)," +
				"    IN Uturn VARCHAR(100)," +
				"    IN Uemail VARCHAR(20)," +
				"    IN Upassword VARCHAR(255)" +
				") " +
				"BEGIN " +
				"    DECLARE idPerson INT; " +
				"    INSERT INTO person(name, surname, lastname, turn) VALUES (Uname, Usurname, Ulastname, Uturn); " +
				"    SET idPerson = LAST_INSERT_ID(); " +
				"    INSERT INTO user(email, password, id_person) VALUES (Uemail, Upassword, idPerson); " +
				"END";

//		jdbcTemplate.execute(sql);
	}
//public static void main(String[] args) {
//	String secretKey = generateSecretKey();
//	System.out.println("Generated Secret Key: " + secretKey);
//}
//
//	public static String generateSecretKey() {
//		SecureRandom secureRandom = new SecureRandom();
//		byte[] key = new byte[32];
//		secureRandom.nextBytes(key);
//
//		return Base64.getEncoder().encodeToString(key);
//	}
}
