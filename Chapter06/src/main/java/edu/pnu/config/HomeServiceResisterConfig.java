package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.pnu.service.HomeServiceImpl;

//@Configuration
public class HomeServiceResisterConfig {

	@Bean
	public HomeServiceImpl homeServiceImpl() {
		return new HomeServiceImpl();
	}
}