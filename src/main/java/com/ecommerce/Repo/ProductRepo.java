package com.ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.model.Products;
@Repository
public interface ProductRepo extends JpaRepository<Products, Integer> {
	

}
