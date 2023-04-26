package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security.authorizeRequests().antMatchers("/").permitAll()
									.antMatchers("/member/**").authenticated()
									.antMatchers("/manager/**").hasRole("MANAGER")
									.antMatchers("/admin/**").hasRole("ADMIN");
		security.csrf().disable();
		security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true);
		security.exceptionHandling().accessDeniedPage("/accessDenied");
        return security.build();
    } // authorizeUrl은 빌더 패턴을 사용함
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("manager")
									 .password("{noop}manager123")
									 .roles("MANAGER");
		auth.inMemoryAuthentication().withUser("admin")
									 .password("{noop}admin123")
									 .roles("ADMIN");
	}
}