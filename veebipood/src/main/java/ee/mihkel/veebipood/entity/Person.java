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
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    // @OneToMany
    //private List<Address> addresses;
}


//{
//        "email": "test@test.com",
//        "password": "midagi",
//        "firstName": "FirstName",
//        "lastName": "LastName",
//        "address": {
//        "street": "Tammsaare",
//        "houseNo": "72",
//        "apartmentNo": "3",
//        "postalIndex": "71333",
//        "city": "Tallinn",
//        "county": "Harjumaa",
//        "country": "Estonia"
//        }
//        }
