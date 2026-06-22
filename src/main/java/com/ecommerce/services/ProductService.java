package com.ecommerce.services;

import java.util.List;

import com.ecommerce.model.Products;

public interface ProductService {

	public Products SaveProduct(Products products);
    public List<Products> GetProducts();
    public Products GetById(int id);
    public Products UpdateProducts(int id, Products product);
    public void Deleteproduct(int id);
	
}
