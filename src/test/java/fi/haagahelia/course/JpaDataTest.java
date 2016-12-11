package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.domain.Member;
import fi.haagahelia.course.domain.MemberRepository;
import fi.haagahelia.course.domain.Membership;
import fi.haagahelia.course.domain.MembershipRepository;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaDataTest {
	
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private MembershipRepository repository2;
	
	@Autowired 
	private UserRepository repository3;
	
	// Test searching
	@Test
	public void findByMember() {
		List<Member> members = repository.findByFirstName("John");
		assertThat(members).hasSize(1);
		assertThat(members.get(0).getEmail()).isEqualTo("johnsnow@got.hbo");
	}
	@Test
	public void findByMembership() {
		List<Membership> memberships = repository2.findByName("member");
		assertThat(memberships.get(0).getName()).isEqualTo("member");
	}
	@Test
	public void findByUser() {
		User users = repository3.findByUsername("john");
		assertThat(users.getRole()).isEqualTo("ADMIN");
	}	
	
	// Test creating
	@Test
	public void createNewMember() {
		Member member = new Member("Test", "Test", "TaiKai@hotmail.com", "2007-11-12",
				new Membership("Test"), new User("Test", 
						"$2a$10$co9kWslF6lNGQMFzTZUCdeGnJ/d7mNhXAIXM7QcnvcfRpj.Lwjsda", 
						"ADMIN"));
		repository.save(member);
		assertThat(member.getId()).isNotNull();
	}
	@Test
	public void createNewMembership() {
		Membership membership = new Membership("Membership");
		repository2.save(membership);
		assertThat(membership.getMembershipid()).isNotNull();
	}
	@Test
	public void createNewUser() {
		User user = new User("test", 
				"$2a$10$co9kWslF6lNGQMFzTZUCdeGnJ/d7mNhXAIXM7QcnvcfRpj.Lwjsda", "USER");
		repository3.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	// Test deleting
	@Test
	public void deleteMember() {
		List<Member> member = repository.findByFirstName("Test");
		
		if(member != null) {
			repository.delete(member);
		}
	}
	@Test
	public void deleteMembership() {
		List<Membership> membership = repository2.findByName("Membership");
		
		if(membership != null) {
			repository2.delete(membership);
		}
	}
	@Test
	public void deleteUser() {
		User user = repository3.findByUsername("test");
		
		if(user != null) {
			repository3.delete(user);
		}
	}
}