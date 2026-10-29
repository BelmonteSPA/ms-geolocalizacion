package com.belmonte.ms_geolocalizacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsGeolocalizacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGeolocalizacionApplication.class, args);
	}

}
