package ee.mihkel.veebipood.repository;

import ee.mihkel.veebipood.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; // --> kõik asjad sees (kõige rohkem funktsioone tuleb interface-i)

import java.util.Collection;
import java.util.List;
//import org.springframework.data.repository.PagingAndSortingRepository; --> lisaks sorteerimine ja lehekülgede näitamine
//import org.springframework.data.repository.CrudRepository; --> ainult tähtsad asjad

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByOrderByNameAsc();
    Page<Product> findByOrderByNameAsc(Pageable pageable);

    List<Product> findByCategory_Id(Long id);
    Page<Product> findByCategory_Id(Long id, Pageable pageable);

    List<Product> findByNutrients_FatLessThan(int fat);

    List<Product> findByCharacteristics_IdIn(Collection<Long> ids);

    Page<Product> findByNameContains(String search, Pageable pageable);
    Page<Product> findByCategory_IdAndNameContains(Long id, String search, Pageable pageable);


}
