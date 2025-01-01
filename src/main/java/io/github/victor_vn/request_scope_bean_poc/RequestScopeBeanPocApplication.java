package io.github.victor_vn.request_scope_bean_poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RequestScopeBeanPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestScopeBeanPocApplication.class, args);
	}

}
