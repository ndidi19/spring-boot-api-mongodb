package com.ndiaye.mongo.mongoapi;

import com.ndiaye.mongo.mongoapi.model.Address;
import com.ndiaye.mongo.mongoapi.model.Gender;
import com.ndiaye.mongo.mongoapi.model.User;
import com.ndiaye.mongo.mongoapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address(
					"99 BoilerPlate Street",
					"23BLP",
					"NY",
					"United States"
			);
			User u = new User(
					"John",
					"Doe",
					"john_doe",
					"john.doe@gmail.com",
					Gender.MALE,
					address,
					LocalDateTime.now()
			);

//			Query query = new Query();
//			query.addCriteria(Criteria.where("email").is(u.getEmail()));
//
//			List<User> userList =  mongoTemplate.find(query, User.class);
//
//			if(!userList.isEmpty()) {
//				System.out.println("User already exists for email : " + u.getEmail());
//			} else {
//				System.out.println("Insert new User : " + u);
//				userRepository.insert(u);
//			}

			userRepository.findUserByEmail(u.getEmail())
					.ifPresentOrElse(user -> {
						System.out.println("User already exists for email : " + u.getEmail());
					}, () -> {
						System.out.println("Insert new User : " + u);
						userRepository.insert(u);
					});
		};
	}
}
