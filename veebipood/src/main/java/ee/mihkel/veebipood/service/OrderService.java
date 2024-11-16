package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.CartRow;
import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    // veateade --> Ultimate's
    @Autowired
    ProductRepository productRepository;

    public double calculateSum(Order order) {
        double sum = 0;
        for (CartRow cr: order.getCartRows()) {
            Product product = productRepository.findById(cr.getProduct().getName()).orElseThrow();
            sum = sum + product.getPrice() * cr.getQuantity();
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
}
