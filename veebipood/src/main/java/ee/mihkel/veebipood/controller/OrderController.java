package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.repository.ProductRepository;
import ee.mihkel.veebipood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//{
//        "id": "see genereeritakse ise @GeneratedValue kaudu",
//        "creation": "teen päringut vastu võttes .setCreation()",
//        "totalSum": "panin juba 100",
//        "products": [
//        {"name": "Fanta"},
//        {"name": "COca"}
//        ]
//        }

@CrossOrigin("http://localhost:4200")
@RestController
public class OrderController {

    // OrderRepository orderRepository = new OrderRepostiory();
    @Autowired
    OrderRepository orderRepository;

//    @Autowired
//    ProductRepository productRepository;

    // seob ühe faili teisega: Dependency Injection
    // 1 mälukoht üle terve projekti -> ei pea tegema kunagi = new ProductRepository();

    @Autowired
    OrderService orderService;

    // POST localhost:8080/orders
    @PostMapping("orders")
    public List<Order> addOrder(@RequestBody Order order) {
        order.setCreation(new Date());
        // ctrl + alt + m
//        OrderService orderService = new OrderService();
        double sum = orderService.calculateSum(order);
        order.setTotalSum(sum);
        orderRepository.save(order);
        return orderRepository.findByPerson_Id(order.getPerson().getId());
    }

    // Controlleris ei tohiks ühtegi funktsiooni olla, millel pole
    // @Mapping

    // Controlleri ülesanne võiks olla ainult päringute vastu võtmine ja
    // front-endile andmete tagastamine





    // localhost:8080/person-orders/5
    @GetMapping("person-orders/{id}")
    public List<Order> getPersonOrders(@PathVariable Long id) {
        return orderRepository.findByPerson_Id(id);
    }




    // localhost:8080/orders-larger-than?minSum=40
    @GetMapping("orders-larger-than")
    public List<Order> ordersLargerThan(@RequestParam double minSum) {
        return orderRepository.findByTotalSumGreaterThan(minSum);
    }

    @GetMapping("order-proteins/{id}")
    public int proteinsInOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return orderService.calculateProteins(order);
    }


}

// Kui paneme koodi käima "run" -->
// Luuakse classid + constructorid + autowired
// Siis jääb seisma

// CRON --> võib ka käivitada. puhastus. e-maili meeldetuletused.
// Controller: Eesmärgiga päringuid vastu võtta
// front-endiga suhtlemiseks

// Entity: andmebaasimudel
// Eesmärk on andmemudel tekitada, mis on ka andmebaasis tabelina

// Repository: andmebaasiga suhtlemiseks -> andmete võtmiseks, lisamiseks, muutmiseks jne

// Service:
