package com.rs.listing.Restuarant.listing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RestuarantListingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestuarantListingApplication.class, args);
	}

}
