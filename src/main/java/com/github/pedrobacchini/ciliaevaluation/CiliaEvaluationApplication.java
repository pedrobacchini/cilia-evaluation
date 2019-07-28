package com.github.pedrobacchini.ciliaevaluation;

import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import com.github.pedrobacchini.ciliaevaluation.entity.Order;
import com.github.pedrobacchini.ciliaevaluation.entity.OrderItem;
import com.github.pedrobacchini.ciliaevaluation.entity.Product;
import com.github.pedrobacchini.ciliaevaluation.repository.ClientRepository;
import com.github.pedrobacchini.ciliaevaluation.repository.OrderItemRepository;
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

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public CiliaEvaluationApplication(OrderRepository orderRepository,
                                      ClientRepository clientRepository,
                                      ProductRepository productRepository,
                                      OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public static void main(String[] args) { SpringApplication.run(CiliaEvaluationApplication.class, args); }

    @Override
    public void run(String... args) throws ParseException {

//        ====================== Clients ===========================

        Client pedro = new Client("Pedro Bacchini", "pedrobacchini@outlook.com");
        Client maria = new Client("Maria Silva", "mariasilva@outlook.com");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        maria.setBirthdate(simpleDateFormat.parse("13/05/1990"));

        List<Client> clients = Arrays.asList(pedro, maria);
        clientRepository.saveAll(clients);
        clients.forEach(System.out::println);

//        ====================== Products ===========================

        Product computer = new Product("Computer", 2000D);
        computer.setDescription("This model features the high-speed, high-performance Intel Core I5 Processor 3.20 ghz, allowing you to open multiple tabs in your browser, watch movies, play games and use other programs without the loss of speed and performance.\n" +
                "\n" +
                "Storage on your 2TB hard drive gives you plenty of storage space for documents, photos, videos, files and movies for you to enjoy freely without having to remove important files for lack of space.\n" +
                "Ram Memory: 8gb Ddr3 1333mhz, making your computer faster powerful in its daily activities.\n" +
                "Intel processor + chipset that guarantees incredible performance and speed.\n" +
                "Dvdrw: to meet all your DVD read and write needs like Movies, Music, etc...");
        Product printer = new Product("Printer", 800D);
        printer.setDescription("HP All-in-One Printer - DeskJet Ink Advantage 3776 Wi-Fi Inkjet\n" +
                "\n" +
                "Save space, money and print wirelessly with the world's smallest multifunction printer. " +
                "Get low-cost color and get all the power you need with amazing, compact styling. Print, " +
                "scan and copy from virtually anywhere with the powerful portable all-in-one printer. " +
                "The small, powerful all-in-one printer saves space and has all the power you need. HP " +
                "Scroll Scan helps you easily handle most scanning jobs on a variety of paper in any " +
                "room or anywhere, this extremely compact all-in-one printer is designed to fit and fit " +
                "where you need it. Show off your style with a sleek design, print quickly from your " +
                "mobile device, the easiest way to print documents, photos, and more from your Apple, " +
                "Android, and Windows devices. Connect your smartphone or tablet directly to your printer, " +
                "and print easily. Scan any object on the go with the HP All-in-One Printer Remote mobile app. " +
                "Fits your budget and fits your needs Count on quality printing with genuine HP ink cartridges. " +
                "Easily recycle genuine HP supplies free of charge through the HP Planet Partners program.");
        Product mouse = new Product("Mouse", 80D);
        Product officeDesk = new Product("Office desk", 300D);

        List<Product> products = Arrays.asList(computer, printer, mouse, officeDesk);
        productRepository.saveAll(products);
        products.forEach(System.out::println);

//        ====================== Orders ===========================

        Order order1 = new Order(pedro);
        Order order2 = new Order(pedro);
        Order order3 = new Order(maria);

//        Updated clients with orders
//        pedro.getOrders().addAll(Arrays.asList(order1, order2));
//        maria.getOrders().add(order3);

        List<Order> orders = Arrays.asList(order1, order2, order3);
        orderRepository.saveAll(orders);
        orders.forEach(System.out::println);

//        Create Order Items
        OrderItem orderItem1 = new OrderItem(order1, computer, 4);
        OrderItem orderItem2 = new OrderItem(order1, mouse, 2);
        OrderItem orderItem3 = new OrderItem(order1, officeDesk, 1);

        OrderItem orderItem4 = new OrderItem(order2, printer, 1);
        OrderItem orderItem5 = new OrderItem(order2, officeDesk, 1);

        OrderItem orderItem6 = new OrderItem(order3, computer, 1);

        List<OrderItem> orderItems = Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4, orderItem5, orderItem6);
        orderItemRepository.saveAll(orderItems);
        orderItems.forEach(System.out::println);

//        Updated orders with order items
        order1.getItens().addAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
        order2.getItens().addAll(Arrays.asList(orderItem4, orderItem5));
        order3.getItens().add(orderItem6);
        orders.forEach(System.out::println);

//        Updated products with order items
//        computer.getItens().addAll(Arrays.asList(orderItem1, orderItem6));
//        mouse.getItens().add(orderItem2);
//        printer.getItens().add(orderItem4);
//        officeDesk.getItens().addAll(Arrays.asList(orderItem3, orderItem5));
    }
}
