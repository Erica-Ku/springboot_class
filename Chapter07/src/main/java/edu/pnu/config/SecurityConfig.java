package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private BoardUserDetailsService boardUserDetailsService;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security.authorizeRequests().antMatchers("/").permitAll()
									.antMatchers("/member/**").authenticated()
									.antMatchers("/manager/**").hasRole("MANAGER")
									.antMatchers("/admin/**").hasRole("ADMIN");
		security.csrf().disable();
		security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true);
		security.exceptionHandling().accessDeniedPage("/accessDenied");
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
		security.userDetailsService(boardUserDetailsService);
        return security.build();
    } // authorizeUrl은 빌더 패턴을 사용함
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}