package com.github.pedrobacchini.ciliaevaluation;

import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import com.github.pedrobacchini.ciliaevaluation.entity.Product;
import com.github.pedrobacchini.ciliaevaluation.repository.ClientRepository;
import com.github.pedrobacchini.ciliaevaluation.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CiliaEvaluationApplication implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public CiliaEvaluationApplication(ClientRepository clientRepository,
                                      ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) { SpringApplication.run(CiliaEvaluationApplication.class, args); }

    @Override
    public void run(String... args) throws ParseException {
        Client client1 = new Client("Pedro Bacchini", "pedrobacchini@outlook.com");
        Client client2 = new Client("Maria Silva", "mariasilva@outlook.com");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        client2.setBirthdate(simpleDateFormat.parse("13/05/1990"));

        List<Client> clients = Arrays.asList(client1, client2);
        clientRepository.saveAll(clients);
        clients.forEach(System.out::println);

        System.out.println(client1.toString());
        System.out.println(client2.toString());

        Product product1 = new Product("Computer", 2000D);
        product1.setDescription("This model features the high-speed, high-performance Intel Core I5 Processor 3.20 ghz, allowing you to open multiple tabs in your browser, watch movies, play games and use other programs without the loss of speed and performance.\n" +
                "\n" +
                "Storage on your 2TB hard drive gives you plenty of storage space for documents, photos, videos, files and movies for you to enjoy freely without having to remove important files for lack of space.\n" +
                "\n" +
                "Ram Memory: 8gb Ddr3 1333mhz, making your computer faster powerful in its daily activities.\n" +
                "\n" +
                "Intel processor + chipset that guarantees incredible performance and speed.\n" +
                "\n" +
                "Dvdrw: to meet all your DVD read and write needs like Movies, Music, etc...");
        Product product2 = new Product("Printer", 800D);
        Product product3 = new Product("Mouse", 80D);
        Product product4 = new Product("Office desk", 300D);

        List<Product> products = Arrays.asList(product1, product2, product3, product4);
        productRepository.saveAll(products);
        products.forEach(System.out::println);
    }
}
