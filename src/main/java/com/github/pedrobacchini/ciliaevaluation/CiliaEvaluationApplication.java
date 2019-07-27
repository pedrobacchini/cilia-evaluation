package com.github.pedrobacchini.ciliaevaluation;

import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import com.github.pedrobacchini.ciliaevaluation.entity.Order;
import com.github.pedrobacchini.ciliaevaluation.entity.Product;
import com.github.pedrobacchini.ciliaevaluation.repository.ClientRepository;
import com.github.pedrobacchini.ciliaevaluation.repository.OrderRepository;
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
    private final OrderRepository orderRepository;

    public CiliaEvaluationApplication(ClientRepository clientRepository,
                                      ProductRepository productRepository,
                                      OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public static void main(String[] args) { SpringApplication.run(CiliaEvaluationApplication.class, args); }

    @Override
    public void run(String... args) throws ParseException {

        Client pedro = new Client("Pedro Bacchini", "pedrobacchini@outlook.com");
        Client maria = new Client("Maria Silva", "mariasilva@outlook.com");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        maria.setBirthdate(simpleDateFormat.parse("13/05/1990"));

        List<Client> clients = Arrays.asList(pedro, maria);
        clientRepository.saveAll(clients);
        clients.forEach(System.out::println);

        Product computer = new Product("Computer", 2000D);
        computer.setDescription("This model features the high-speed, high-performance Intel Core I5 Processor 3.20 ghz, allowing you to open multiple tabs in your browser, watch movies, play games and use other programs without the loss of speed and performance.\n" +
                "\n" +
                "Storage on your 2TB hard drive gives you plenty of storage space for documents, photos, videos, files and movies for you to enjoy freely without having to remove important files for lack of space.\n" +
                "\n" +
                "Ram Memory: 8gb Ddr3 1333mhz, making your computer faster powerful in its daily activities.\n" +
                "\n" +
                "Intel processor + chipset that guarantees incredible performance and speed.\n" +
                "\n" +
                "Dvdrw: to meet all your DVD read and write needs like Movies, Music, etc...");
        Product printer = new Product("Printer", 800D);
        Product mouse = new Product("Mouse", 80D);
        Product officeDesk = new Product("Office desk", 300D);

        List<Product> products = Arrays.asList(computer, printer, mouse, officeDesk);
        productRepository.saveAll(products);
        products.forEach(System.out::println);

        Order order1 = new Order(pedro);
        Order order2 = new Order(pedro);
        Order order3 = new Order(maria);

        pedro.getOrders().addAll(Arrays.asList(order1, order2));
        maria.getOrders().add(order3);

        List<Order> orders = Arrays.asList(order1, order2, order3);
        orderRepository.saveAll(orders);
        orders.forEach(System.out::println);
    }
}
