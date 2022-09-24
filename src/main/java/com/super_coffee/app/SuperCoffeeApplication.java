package com.super_coffee.app;

import com.super_coffee.app.models.domain.Role;
import com.super_coffee.app.repository.IRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication @Slf4j
public class SuperCoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperCoffeeApplication.class, args);
	}

	/**
	 * Define default roles when they do not exist
	 * @param roleRepository contains the methods of business logic
	 * @return a lambda with the operations
	 */
	@Bean
	CommandLineRunner run( IRoleRepository roleRepository )
	{
		return args -> {
			Role ADMIN = new Role( "ADMIN_ROLE" );
			Role USER = new Role( "USER_ROLE" );
			Role SALES = new Role( "SALES_ROLE" );

			roleRepository.findByDescription( ADMIN.getDescription() ).ifPresentOrElse(( value ) -> {
				log.info( "Created Successfully role:" + value.getDescription() );
			}, () -> roleRepository.save(ADMIN) );

			roleRepository.findByDescription( USER.getDescription() ).ifPresentOrElse(( value ) -> {
				log.info( "Created Successfully role:" + value.getDescription() );
			}, () -> { roleRepository.save(USER); } );

			roleRepository.findByDescription( SALES.getDescription() ).ifPresentOrElse(( value ) -> {
				log.info( "Created Successfully role:" + value.getDescription() );
			}, () -> { roleRepository.save(SALES); } );
		};
	}
}
