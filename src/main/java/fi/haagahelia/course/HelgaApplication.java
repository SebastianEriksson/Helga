package fi.haagahelia.course;

import java.time.LocalDate;

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
import fi.haagahelia.course.domain.Position;
import fi.haagahelia.course.domain.PositionRepository;

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
	public CommandLineRunner bookDemo(MemberRepository repository, PositionRepository prepository) {
		return (args) -> {
			log.info("Add a couple of test users to the database");
			
			// Create pre-made positions
			prepository.save(new Position("Member"));
			prepository.save(new Position("Tutor"));
			prepository.save(new Position("Headtutor"));
			prepository.save(new Position("Helga"));
			prepository.save(new Position("Admin"));
			
			// Create pre-made users
			
			repository.save(new Member("John", "Snow", "JohnSnow@GOT.hbo", 
					LocalDate.now().plusMonths(12), "yes", "john", 
					"$2a$10$ClgJRD8V9bQewxWz7gw04.jiQBb7uENBwaXb1G4/oI/0n6WXEIw0i", 
					"member", prepository.findByName("Tutor").get(0)));
			
			// Show all the members in the log at startup
			log.info("show all the members in the database");
			for (Member member : repository.findAll()) {
				log.info(member.toString());
			}
		};
	}
}
