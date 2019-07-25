package com.github.pedrobacchini.ciliaevaluation;

import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import com.github.pedrobacchini.ciliaevaluation.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CiliaEvaluationApplication implements CommandLineRunner {

    private final ClientRepository clientRepository;

    public CiliaEvaluationApplication(ClientRepository clientRepository) { this.clientRepository = clientRepository; }

    public static void main(String[] args) { SpringApplication.run(CiliaEvaluationApplication.class, args); }

    @Override
    public void run(String... args) {
        Client client1 = new Client("Pedro Bacchini", "pedrobacchini@outlook.com");
        Client client2 = new Client("Maria Silva", "mariasilva@outlook.com");

        clientRepository.saveAll(Arrays.asList(client1, client2));

        System.out.println(client1.toString());
        System.out.println(client2.toString());
    }
}
