package com.ecommerce.services;

import java.util.List;

import com.ecommerce.model.Category;


public interface CategoryService {
	public Category SaveCategory(Category Category);
	public List<Category> getAllData();
	public Boolean existsCategory(String name);
	public void Catdelete(int id);
	public Category getCById(int id);

}
