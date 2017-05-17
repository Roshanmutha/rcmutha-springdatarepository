package com.rcmutha.springjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.rcmutha.springjpa.entity.Customer;
import com.rcmutha.springjpa.service.CustomerRepository;

@SpringBootApplication
@ComponentScan(value = "com.rcmutha")
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	/**
	 * 
	 * Refer https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
	 * 
	 * Search Table 4. "Supported keywords inside method names."
	 * 
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer", "India"));
			repository.save(new Customer("Chloe", "O'Brian", "US"));
			repository.save(new Customer("Kim", "Bauer", "Germany"));
			repository.save(new Customer("David", "Palmer", "India"));
			repository.save(new Customer("Michelle", "Dessler", "China"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");

			// fetch customers by (lastname AND firstName)
			log.info("Customer found with findByLastNameAndFirstName('kim','Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastNameAndFirstName("Bauer", "Kim")) {
				log.info(bauer.toString());
			}
			log.info("");
			// fetch customers by (lastname AND address)
			log.info("Customer found with findByLastNameOrAddress('Palmer','India'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastNameAndAddress("Palmer", "India")) {
				log.info(bauer.toString());
			}
			log.info("");

		};
	}

}