package org.aguzman.springcloud.msvc.reservas;

import org.aguzman.springcloud.msvc.reservas.controllers.ReservaRestController;
import org.aguzman.springcloud.msvc.reservas.dto.ReservaDTO;
import org.aguzman.springcloud.msvc.reservas.models.entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDate;
import java.util.List;

@EnableFeignClients
@SpringBootApplication
public class MsvcReservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcReservasApplication.class, args);
	}

}
