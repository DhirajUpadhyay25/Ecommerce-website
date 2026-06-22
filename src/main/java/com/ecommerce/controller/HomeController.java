package com.ecommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.services.CategoryService;
import com.ecommerce.services.ProductService;

@Controller
public class HomeController {
	@Autowired
    private ProductService Pservice;
	
	@Autowired
    private CategoryService Cservice;
	
//	  @GetMapping("/List_products")
//	    public String ViewPeoducts(Model model ) {
//	     List<Products> list = Pservice.GetProducts();
//	     model.addAttribute("products", list);
//	     model.addAttribute("totalProducts", list.size());
//		return "admin/ViewProduct";
//	    
//	    }
	
	  @GetMapping("/")
	  public String indexget(Model model) {
		  model.addAttribute("category",Cservice.getAllData());
		  model.addAttribute("product",Pservice.GetProducts());
		return "index";
		  
	  }
	  @GetMapping("/login")
	  public String Login() {
		  
		return "login";
		  
	  }
	  @GetMapping("/signup")
	  public String signups() {
		  
		return "signup";
		  
	  }
	  //products 
	  @GetMapping("/product")
	  public String product(Model model) {
		  model.addAttribute("category",Cservice.getAllData());
		  model.addAttribute("product",Pservice.GetProducts());
		return "product";
		  
	  }
	  @GetMapping("/feedback")
	  public String feedback() {
		  
		return "feedback";
		  
	  }
	  @GetMapping("/view_product")
	  public String viewproduct() {
		  
		return "viewproduct";
		  
	  }
	  
	  
	  
}
