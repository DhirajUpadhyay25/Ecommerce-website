package com.ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	public boolean existsByName(String name);
}
