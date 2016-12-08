package fi.haagahelia.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.haagahelia.course.web.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	// Rules the system implements for anyone connecting
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
			.and()
			.authorizeRequests().antMatchers("/signup", "/saveuser").permitAll() // Allow members to signup
	        .and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login") // What page will be the first to be loaded when connecting
			.defaultSuccessUrl("/memberlist") // If login is successful, switch to the defined page
			.permitAll() // Permit everyone to access the login page
			.and()
		.logout()
			.permitAll(); // Give everyone permission to logout
	}
	
	// Encode the password
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
