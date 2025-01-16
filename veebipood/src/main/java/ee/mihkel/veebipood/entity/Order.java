package ee.mihkel.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
// nii "order" kui ka "user" on Postres juba reserveeritud
@Table(name = "orders")
@SequenceGenerator(name = "seq", initialValue = 313134200, allocationSize = 1)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq") // 1, 2, 3, 4, 5 ....
    private Long id;
    private Date creation;
    private double totalSum;

    // kui pole Listi, ja ei pane paremale One, siis lihtsalt ei lähe käima
    // ManyToOne  --> parempoolne on One  -  mul ei ole listi
    // OneToOne  --> parempoolne on One  -  mul ei ole listi
    @ManyToOne
    private Person person;

    //@OneToOne
    //private EmailPhone emailPhone; ---> siis kui sisselogimata

    // OneToOne --> täpselt 1 tabeli kirje on seotud teise tabeliga täpselt ühe kirjega.
    // kui kustutakse ära see, kelle küljes ta on, siis kustub ära ka kirje teisest tabelist.

    // ManyToOne --> ühe tabeli kirje küljes võib olla mitmeid teise tabeli kirjeid.
    // Ühe Personi küljes võib olla mitmeid Ordereid.

    // kui on List ja panen paremale One siis lihtsalt ei lähe käima
    // ManyToMany  --> parempoolne on Many  -  mul on list
    // OneToMany  --> parempoolne on Many  -  mul on list
//    @ManyToMany
//    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL)
    public List<CartRow> cartRows;

    // @ManyToOne
    // public Player player;

    // @OneToOne
    //       ainsuses
    // Kui kustutan selle entity kelle küljes ta on, kustub ka tema
    // @OneToMany
    //       mitmuses
    // Kui kustutan selle entity kelle küljes ta on, kustub ka nemad
}


//{
//        "person": {"id": 1},
//        "cartRows": [
//        {"quantity": 2, "product": {"name": "Piim"}},
//        {"quantity": 1, "product": {"name": "Sulatatud juust"}}
//        ]
//        }