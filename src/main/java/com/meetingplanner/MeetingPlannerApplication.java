package com.meetingplanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class MeetingPlannerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MeetingPlannerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
