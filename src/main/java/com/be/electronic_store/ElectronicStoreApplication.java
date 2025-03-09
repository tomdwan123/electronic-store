package com.be.electronic_store;

import com.be.electronic_store.entity.Role;
import com.be.electronic_store.entity.User;
import com.be.electronic_store.repository.RoleRepository;
import com.be.electronic_store.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ElectronicStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(RoleRepository roleRepository, UserRepository userRepository) {

		return args -> {

			// add roles
			Role roleAdmin = Role
					.builder()
						.name("ROLE_ADMIN")
					.build();
			Role roleCustomer = Role
					.builder()
						.name("ROLE_CUSTOMER")
					.build();

			roleRepository.saveAll(List.of(roleAdmin, roleCustomer));

			// add user admin and customer
			User admin = User
						.builder()
							.firstName("Super")
							.lastName("Admin")
							.email("admin@gmail.com")
							.role(roleAdmin)
						.build();
			User tom =	User
							.builder()
							.firstName("Mr.")
							.lastName("Tom")
							.email("tom@gmail.com")
							.role(roleCustomer)
						.build();
			User jerry = User
						.builder()
							.firstName("Mr.")
							.lastName("Jerry")
							.email("jerry@gmail.com")
							.role(roleCustomer)
						.build();
			userRepository.saveAll(List.of(admin, tom, jerry));
		};
	}
}
