package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.CartRow;
import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.models.EveryPayCheck;
import ee.mihkel.veebipood.models.EveryPayData;
import ee.mihkel.veebipood.models.EveryPayLink;
import ee.mihkel.veebipood.models.EveryPayResponse;
import ee.mihkel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    // veateade --> Ultimate's
    @Autowired
    ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${everypay.url}")
    private String url;

    @Value("${everypay.username}")
    private String username;

    @Value("${everypay.password}")
    private String password;

    @Value("${everypay.customerUrl}")
    private String customerUrl;

    public double calculateSum(Order order) {
        double sum = 0;
        for (CartRow cr: order.getCartRows()) {
            Optional<Product> product = productRepository.findById(cr.getProduct().getName());
            if (product.isEmpty()) {
                throw new RuntimeException("Tellimust tehes sellise nimega toodet ei leitud!");
            } else {
                sum = sum + product.get().getPrice() * cr.getQuantity();
            }
            // sum += p.getPrice();
        }
        return sum;
    }

    public int calculateProteins(Order order) {
        int proteins = 0;
        for (CartRow cartRow: order.getCartRows()) {
            proteins += cartRow.getProduct().getNutrients().getProtein();
        }
        return proteins;
    }

    public EveryPayLink getPaymentLink(Order dbOrder) {

//        String url = "https://igw-demo.every-pay.com/api/v4/payments/oneoff";
//        String username = "92ddcfab96e34a5f";
//        String password = "8cd19e99e9c2c208ee563abf7d0e4dad";

        // body
        EveryPayData body = new EveryPayData();
        body.setAccount_name("EUR3D1");
        body.setNonce("suvaline" + Math.random() + new Date());
        body.setTimestamp(ZonedDateTime.now().toString());
        body.setAmount(dbOrder.getTotalSum());
        body.setOrder_reference(dbOrder.getId().toString());
        body.setCustomer_url(customerUrl);
        body.setApi_username(username);

        // headers
        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.AUTHORIZATION, "Basic OTJkZGNmYWI5NmUzNGE1Zjo4Y2QxOWU5OWU5YzJjMjA4ZWU1NjNhYmY3ZDBlNGRhZA==");
        headers.setBasicAuth(username, password);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // päring ise
        HttpEntity<EveryPayData> entity = new HttpEntity<>(body, headers);
        ResponseEntity<EveryPayResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, EveryPayResponse.class);

        EveryPayLink link = new EveryPayLink();
        link.setUrl(response.getBody().getPayment_link());
        return link;
    }

    public boolean checkPaymentStatus(String paymentRef) {
//        String username = "92ddcfab96e34a5f";
//        String password = "8cd19e99e9c2c208ee563abf7d0e4dad";

        String url = "https://igw-demo.every-pay.com/api/v4/payments/" +
                paymentRef + "?api_username=" + username + "&detailed=false";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        HttpEntity entity = new HttpEntity<>(null, headers);


//         https://igw-demo.every-pay.com/api/v4/payments/8e7d202424f3b4e4cbd294b60f02408d118bbddb1ca6206f5c728fd00efbe798?api_username=92ddcfab96e34a5f&detailed=false
        ResponseEntity<EveryPayCheck> response = restTemplate.exchange(url, HttpMethod.GET, entity, EveryPayCheck.class);
        return response.getBody().getPayment_state().equals("settled");
    }
}
