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
	
	// Add pre-made memberships, test users with login access
	@Bean
	public CommandLineRunner bookDemo(MemberRepository repository, MembershipRepository mrepository, UserRepository urepository) {
		return (args) -> {
			log.info("Add a couple of test memberships, users and mebers to the database");
			
			// Create pre-made memberships
			mrepository.save(new Membership("member"));
			mrepository.save(new Membership("tutor"));
			mrepository.save(new Membership("headtutor"));
			mrepository.save(new Membership("helga"));
			mrepository.save(new Membership("admin"));
			
			// Create pre-made users for members
			User user1 = new User("kai", 
					"$2a$10$52QRSr3MhTnsJilL/eghwOTXB4oYbFVhBeRxsnUCQ9Y8Er2qhCq92", 
					"USER");
			User user2 = new User("john", 
					"$2a$10$HaTVlLziVQhbIh/ZKVrg1.LOZ7js07DGMhcjf1DHqCJN/Bo.03tZe", 
					"ADMIN");
			User user3 = new User("tommi", 
					"$2a$10$52QRSr3MhTnsJilL/eghwOTXB4oYbFVhBeRxsnUCQ9Y8Er2qhCq92", 
					"USER");
			User user4 = new User("snowden", 
					"$2a$10$52QRSr3MhTnsJilL/eghwOTXB4oYbFVhBeRxsnUCQ9Y8Er2qhCq92", 
					"ADMIN");
			User user5 = new User("sarah", 
					"$2a$10$52QRSr3MhTnsJilL/eghwOTXB4oYbFVhBeRxsnUCQ9Y8Er2qhCq92", 
					"USER");
			User user6 = new User("timi", 
					"$2a$10$52QRSr3MhTnsJilL/eghwOTXB4oYbFVhBeRxsnUCQ9Y8Er2qhCq92", 
					"USER");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
			urepository.save(user4);
			urepository.save(user5);
			urepository.save(user6);
			
			// Create pre-made members
			repository.save(new Member("Kai", "Mountain", "Kai@hotmail.com", "2017-12-12", 
					mrepository.findByName("member").get(0), 
					urepository.findByUsername("kai")));
			repository.save(new Member("John", "Snow", "johnsnow@got.hbo", "2017-12-12", 
					mrepository.findByName("headtutor").get(0), 
					urepository.findByUsername("john")));
			repository.save(new Member("Tommi", "Virtanen", "tommi@hotmail.com", "2017-12-14", 
					mrepository.findByName("helga").get(0), 
					urepository.findByUsername("tommi")));
			repository.save(new Member("Snow", "Den", "snowden@hotmail.com", "2017-12-12", 
					mrepository.findByName("admin").get(0), 
					urepository.findByUsername("snowden")));
			repository.save(new Member("Sarah", "Lind", "lind.sarah@gmail.com", "2017-12-12", 
					mrepository.findByName("tutor").get(0), 
					urepository.findByUsername("sarah")));
			repository.save(new Member("Timi", "Koski", "koski@timi.com", "2017-12-12", 
					mrepository.findByName("member").get(0), 
					urepository.findByUsername("timi")));
			
			// Show all the members in the log at startup
			log.info("show all the members in the database");
			for (Member member : repository.findAll()) {
				log.info(member.toString());
			}
		};
	}
}
