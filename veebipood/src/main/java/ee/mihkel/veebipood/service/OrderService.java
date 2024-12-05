package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.CartRow;
import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    // veateade --> Ultimate's
    @Autowired
    ProductRepository productRepository;

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
}
