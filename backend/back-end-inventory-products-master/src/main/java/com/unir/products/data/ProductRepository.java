package com.unir.products.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unir.products.model.pojo.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


// Entidad y tipo de dato del identificador
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameStartsWith(String prefix);

    List<Product> findAllByCategory(String category);

    List<Product> findAllByIsAvailable(boolean isAvailable);

    List<Product> findByCreatedAtBetween(Date dateStart, Date dateEnd);

    List<Product> findAllByPriceGreaterThan(double price);

    List<Product> findAllByPriceLessThan(double price);

    // para querise complejas
    //@Query("SELECT * .----")
}
