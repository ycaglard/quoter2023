package com.quoter.quoter;

import com.quoter.quoter.model.Role;
import com.quoter.quoter.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuoterApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuoterApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(RoleRepository roleRepo) {
		return (args) -> {
			Role role=new Role();
			role.setName("ROLE_ADMIN");
			roleRepo.save(role);
		};
	}
}
