package com.super_coffee.app;

import com.super_coffee.app.models.Role;
import com.super_coffee.app.service.RoleServiceImpl;
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
	CommandLineRunner run( RoleServiceImpl roleService ) {
		return args -> {
			Role ADMIN = new Role( "ADMIN_ROLE" );
			Role USER = new Role( "USER_ROLE" );
			Role SALES = new Role( "SALES_ROLE" );

			roleService.findByDescription( ADMIN.getDescription() ).ifPresentOrElse(( value ) -> {} ,() -> {
				roleService.save( ADMIN );
			});

			roleService.findByDescription( USER.getDescription() ).ifPresentOrElse(( value ) -> {} ,() -> {
				roleService.save( USER );
			});

			roleService.findByDescription( SALES.getDescription() ).ifPresentOrElse(( value ) -> {} ,() -> {
				roleService.save( SALES );
			});
		};
	}
}
