package org.aguzman.springcloud.msvc.habitaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcHabitacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcHabitacionesApplication.class, args);
	}

}
