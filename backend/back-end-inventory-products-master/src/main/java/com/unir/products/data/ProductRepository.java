 package com.unir.products.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unir.products.model.pojo.Product;


// Entidad y tipo de dato del identificador
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByName(String name);
	List<Product> findByCategory(String category);
	
	// para querise complejas
	//@Query("SELECT * .----")
}
