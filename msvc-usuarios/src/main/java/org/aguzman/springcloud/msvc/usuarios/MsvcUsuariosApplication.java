package org.aguzman.springcloud.msvc.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableFeignClients
@SpringBootApplication
public class MsvcUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcUsuariosApplication.class, args);
	}

}
