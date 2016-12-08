package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.web.MemberController;
import fi.haagahelia.course.web.UserController;

/**
 * 
 * Testing that the context is creating your controller
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelgaApplicationTests {
	
	@Autowired
	private MemberController controller;
	
	@Autowired
	private UserController controller2;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		assertThat(controller2).isNotNull();
	}

}
