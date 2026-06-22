package com.ecommerce.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Repo.CategoryRepo;
import com.ecommerce.model.Category;
@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryRepo cRepo;
	
	@Override
	public Category SaveCategory(Category Category) {
		return cRepo.save(Category); 
	}

	@Override
	public List<Category> getAllData() {

		return cRepo.findAll();
	}
	@Override
	public Boolean existsCategory(String name) {
		
		return cRepo.existsByName(name);
	}

	@Override
	public void Catdelete(int id) {
		// TODO Auto-generated method stub
		cRepo.deleteById(id);
	}

	@Override
	public Category getCById(int id) {
	Category category=cRepo.findById(id).orElse(null);
	return category;
	}
}
