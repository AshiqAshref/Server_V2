package com.MainServer.Server_V2;

import com.MainServer.Server_V2.modeA.service.ReminderAService;
import com.MainServer.Server_V2.modeB.service.ReminderBService;
import com.MainServer.Server_V2.modeB.service.ReminderBServiceTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.*;

@SpringBootApplication
public class ServerV2Application {

	public static void main(String[] args) {
		SpringApplication.run(ServerV2Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ReminderAService reminderAService, ReminderBService reminderBService, ReminderBServiceTest reminderBServiceTest){
		return args ->{
			try{

				reminderBServiceTest.test(2);
//				reminderAService.addRandom(5);
			}catch(Exception e){
				e.printStackTrace();
			}
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}