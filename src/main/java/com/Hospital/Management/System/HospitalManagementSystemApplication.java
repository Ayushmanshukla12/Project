package com.Hospital.Management.System;

import com.Hospital.Management.System.entities.UserPojo;
import com.Hospital.Management.System.repository.UserPojoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementSystemApplication.class, args);
	}



//	@PostConstruct
//	public void initUsers(){
//		List<UserPojo> users= Stream.of(new UserPojo(1,"admin",)).collect(Collectors.toList());
//
//		UserPojoRepository.saveAll(users);
//	}
//	@Bean
//	public PasswordEncoder passwordEncoder(){
//		return NoOpPasswordEncoder.getInstance();
//	}


}
