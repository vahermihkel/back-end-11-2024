package ee.mihkel.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//@Table(name = "product")
public class Product {
    @Id
    private String name;
    private double price;
    private boolean active;
    private String image;

    // Võimalus välja tõsta, ei pea välja tõstma
    @OneToOne(cascade = CascadeType.ALL)
    private Nutrients nutrients;

    @ManyToOne
    private Category category;

    @ManyToMany
    private List<Characteristic> characteristics;
}

//{
//        "name": "Coca",
//        "price": 1.1,
//        "active": true,
//        "image": "https://i0.wp.com/thesweetseria.com/wp-content/uploads/2022/09/coca-cola.jpg",
//        "nutrients": {
//        "protein": 1,
//        "carbohydrate": 10,
//        "fat": 2
//        },
//        "category": {
//        "id": 1
//        },
//        "characteristics": [
//        {"id": 2},
//        {"id": 4}
//        ]
//        }
