package com.super_coffee.app;

import com.super_coffee.app.models.domain.Role;
import com.super_coffee.app.service.impl.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SuperCoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperCoffeeApplication.class, args);
	}

	/**
	 * Define default roles when they do not exist
	 * @param roleService contains the methods of business logic
	 * @return a lambda with the operations
	 */
	@Bean
	CommandLineRunner run( RoleService roleService ) {
		return args -> {
			Role ADMIN = new Role( "ADMIN_ROLE" );
			Role USER = new Role( "USER_ROLE" );
			Role SALES = new Role( "SALES_ROLE" );

			if( roleService.findByDescription( ADMIN.getDescription() ) == null ) roleService.save(ADMIN);

			if( roleService.findByDescription( USER.getDescription() ) == null ) roleService.save( USER );

			if( roleService.findByDescription( SALES.getDescription() ) == null) roleService.save( SALES );
		};
	}
}
