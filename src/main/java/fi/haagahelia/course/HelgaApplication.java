package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Member;
import fi.haagahelia.course.domain.MemberRepository;
import fi.haagahelia.course.domain.Membership;
import fi.haagahelia.course.domain.MembershipRepository;
//import fi.haagahelia.course.domain.Role;
//import fi.haagahelia.course.domain.RoleRepository;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;

@SpringBootApplication
public class HelgaApplication extends SpringBootServletInitializer {
	
	// 
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HelgaApplication.class);
	}
	
	// Part of the logging system
	private static final Logger log = LoggerFactory.getLogger(HelgaApplication.class);
	
	// Main
	public static void main(String[] args) {
		SpringApplication.run(HelgaApplication.class, args);
	}
	
	// Add pre-made positions, test users with login access
	@Bean
	public CommandLineRunner bookDemo(MemberRepository repository, MembershipRepository mrepository, UserRepository urepository) {
		return (args) -> {
			log.info("Add a couple of test users to the database");
			
			// Create pre-made role statuses
//			rrepository.save(new User("USER"));
//			rrepository.save(new Role("ADMIN"));
			
			// Create pre-made positions
			mrepository.save(new Membership("member"));
			mrepository.save(new Membership("tutor"));
			mrepository.save(new Membership("headtutor"));
			mrepository.save(new Membership("helga"));
			mrepository.save(new Membership("admin"));
			
			// Create pre-made users for members
			User user1 = new User("kai", 
					"$2a$10$pw6Z8aFUI6T94OauF08U7ubXlKK9yFubDj1jubtn4QuH3z1Epf8Hu", 
					"USER");
			User user2 = new User("john", 
					"$2a$10$HaTVlLziVQhbIh/ZKVrg1.LOZ7js07DGMhcjf1DHqCJN/Bo.03tZe", 
					"ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			// Create pre-made members
			repository.save(new Member("John", "Snow", "johnsnow@got.hbo", "2017-12-12", 
					mrepository.findByName("tutor").get(0), 
					urepository.findByUsername("john")));
			repository.save(new Member("Kai", "Mountain", "Kai@hotmail.com", "2017-12-12", 
					mrepository.findByName("member").get(0), 
					urepository.findByUsername("kai")));
			
			// Show all the members in the log at startup
			log.info("show all the members in the database");
			for (Member member : repository.findAll()) {
				log.info(member.toString());
			}
		};
	}
}
