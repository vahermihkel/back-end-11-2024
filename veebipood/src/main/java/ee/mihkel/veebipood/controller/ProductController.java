package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.repository.ProductRepository;
import ee.mihkel.veebipood.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

//@CrossOrigin("http://localhost:4200")
@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    Random random;

//    private final ProductRepository productRepository;
//
//    public ProductController(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }


//    List<Product> products = new ArrayList<>(Arrays.asList(
//            new Product("Coca", 5, true, ""),
//            new Product("Fanta", 1, false, ""),
//            new Product("Sprite", 3, true, "")
//            ));

    // products = ["Coca", "Fanta", "Sprite]

    // localhost:8080/products
    @GetMapping("products")
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable); // SELECT * FROM products;
    }

    @GetMapping("product/{name}")
    public Product getProduct(@PathVariable String name) {
        return productRepository.findById(name).orElseThrow(); // SELECT * FROM products;
    }

    // kui ma teen brauseris päringuid, siis ma ei saa @PostMapping, @PutMapping, @DeleteMapping
    // localhost:8080/add-product?name=Pepsi&price=2
    //                         @RequestParam String name


    // localhost:8080/products
    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product) {
        //products.add(new Product(name, price, true, ""));
        productRepository.save(product);
        return productRepository.findAll();
    }


    // Variandid, mida tagastada pärast lisamist:
    // List<String> --> uuenenud list
    // String --> Lisatud toode
    // String --> Sõnum, et ilusti lisatud
    // void --> kui errorit ei ole, järelikult õnnestus

    @GetMapping("delete-product/{name}")
    public List<Product> deleteProduct(@PathVariable String name) {
//        Product product = products.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
//        products.remove(product);
        productRepository.deleteById(name); // DELETE FROM products WHERE id = "Coca"
        return productRepository.findAll();
    }

    @GetMapping("random-product")
    public Product randomProduct() {
        List<Product> products = productRepository.findAll();
//        Random random = new Random();
        System.out.println(random);
        int index = random.nextInt(products.size());
        return products.get(index);
    }

    // localhost:8080/category-products?categoryId=1
    @GetMapping("category-products")
    public List<Product> getCategoryProducts(@RequestParam Long categoryId) {
        return productRepository.findByCategory_Id(categoryId); // SELECT * FROM products WHERE category.id = ?;
    }

    // localhost:8080/characteristics-products?ids=1,2,3,4,5
    @GetMapping("characteristics-products")
    public List<Product> getProductsByCharacteristics(@RequestParam List<Long> ids) {
        return productRepository.findByCharacteristics_IdIn(ids);
    }

}
