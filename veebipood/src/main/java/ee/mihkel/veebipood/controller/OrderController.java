package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.CartRow;
import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.models.EveryPayLink;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.repository.PersonRepository;
import ee.mihkel.veebipood.repository.ProductRepository;
import ee.mihkel.veebipood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

//@CrossOrigin("http://localhost:4200")
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
    @Autowired
    private PersonRepository personRepository;

    // POST localhost:8080/orders
    @PostMapping("orders")
    public EveryPayLink addOrder(@RequestBody List<CartRow> cartRows) {
        Order order = new Order();

        Long id =  Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        Person person = personRepository.findById(id).orElseThrow();
        order.setPerson(person);

        order.setCartRows(cartRows);

        order.setCreation(new Date());
        // ctrl + alt + m
//        OrderService orderService = new OrderService();
        double sum = orderService.calculateSum(order);
        order.setTotalSum(sum);
        Order dbOrder = orderRepository.save(order);
        return orderService.getPaymentLink(dbOrder);
    }//payment_reference omab enda URL parameetris infot, kas makse õnnestus või ebaõnnestus
    //SETTLED: ?order_reference=313134204&payment_reference=8e7d202424f3b4e4cbd294b60f02408d118bbddb1ca6206f5c728fd00efbe798
    //SETTLED: ?order_reference=313134205&payment_reference=a59af5194096558f0bbc3f4c5c6a080ade74b2f637d35dd384f96d1218e54ee4
    //FAILED: ?order_reference=313134206&payment_reference=42ce9e77adfbc04504cd78fdafef90b898f24368b9ce29524f88ed4bd0dd6b55

    @GetMapping("check-payment")
    public boolean checkPaymentStatus(@RequestParam String paymentRef) {
        return orderService.checkPaymentStatus(paymentRef);
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
