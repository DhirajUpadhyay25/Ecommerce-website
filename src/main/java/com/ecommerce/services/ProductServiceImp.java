package com.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.Repo.ProductRepo;
import com.ecommerce.model.Products;
@Service
public class ProductServiceImp implements ProductService {
   
	@Autowired
	ProductRepo reposs;
	@Override
	public Products SaveProduct(Products products) {
		// TODO Auto-generated method stub
		return reposs.save(products);
	}
	@Override
	public List<Products> GetProducts() {
		// TODO Auto-generated method stub
		return reposs.findAll();
	}
	@Override
	public Products GetById(int id) {
		// TODO Auto-generated method stub
		Products products= reposs.findById(id).orElse(null);
		return  products;
	}
	
	@Override
	public Products UpdateProducts(int id, Products product) {
		Products oldData= GetById(id);
		if(oldData!=null) {
			oldData.setName(product.getName());
			oldData.setCategory(product.getCategory());
			oldData.setImage(product.getImage());
			oldData.setPrice(product.getPrice());
			oldData.setDescription(product.getDescription());
			oldData.setStatus(product.getStatus());
			oldData.setStock(product.getStock());
		}
		return oldData;
	}
	
	@Override
	public void Deleteproduct(int id) {
		// TODO Auto-generated method stub
		reposs.deleteById(id);
	}

}
