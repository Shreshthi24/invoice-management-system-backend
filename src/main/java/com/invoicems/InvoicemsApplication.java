package com.invoicems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class InvoicemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoicemsApplication.class, args);
	}

}
