package com.experiment.ExperimentSpringBoot;


import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableAdminServer
//@EnableScheduling - When need to test scheduling uncomment this section
public class ExperimentSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExperimentSpringBootApplication.class, args);
	}
}
