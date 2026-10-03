package com.denkitronik.clienteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.denkitronik.clienteservice", "clienteservicie"})
@EnableJpaRepositories(basePackages = {"clienteservicie.domain.repositories"})
@EntityScan(basePackages = {"clienteservicie.domain.entities"})
public class ClienteServicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClienteServicioApplication.class, args);
    }

}