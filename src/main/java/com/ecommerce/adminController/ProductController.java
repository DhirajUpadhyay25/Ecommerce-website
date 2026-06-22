package com.ecommerce.adminController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.Repo.ProductRepo;
import com.ecommerce.model.Category;
import com.ecommerce.model.Products;
import com.ecommerce.services.CategoryService;
import com.ecommerce.services.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	
	@Autowired
    private CategoryService service;
	
	@Autowired
    private ProductService Pservice;
	
    //add product page 
    @GetMapping("/admin/addProduct")
    public String Addproduct(Model model ) {
    	List<Category> categories= service.getAllData();
    	model.addAttribute("categories", categories);
		return "admin/addProduct";
    }
    
    @PostMapping("/saveProduct")
    public String saveproducts(@ModelAttribute Products products,
                               @RequestParam("imgFile") MultipartFile image,
                               HttpSession session) throws IOException {

        String imageName = image.isEmpty() ? "default.jpg" : image.getOriginalFilename();
        products.setImage(imageName);
        session.removeAttribute("succMsg");
        session.removeAttribute("errorMsg");
        Products saveProduct = Pservice.SaveProduct(products);

        if (!ObjectUtils.isEmpty(saveProduct)) {

        	String uploadDir = System.getProperty("user.dir") + "/uploads/product_img/";            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            Path path = Paths.get(uploadDir + imageName);
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            session.setAttribute("succMsg", "Product Saved Successfully");

        } else {
            session.setAttribute("errorMsg", "Something went wrong");
        }

        return "redirect:/admin/addProduct";
    }
 
    // View products 
    @GetMapping("/List_products")
    public String ViewPeoducts(Model model ) {
     List<Products> list = Pservice.GetProducts();
     model.addAttribute("products", list);
     model.addAttribute("totalProducts", list.size());
	return "admin/ViewProduct";
    
    }
    
    @GetMapping("/delete_products/{id}")
    public String Deleteproduct(@PathVariable int id) {
    	Pservice.Deleteproduct(id);
    	
    	return "redirect:/List_products";
    }
     
    @GetMapping("/updateProduct/{id}")
     public String UpdateData(@PathVariable int id, Model model) {
    	
    	model.addAttribute("prod", Pservice.GetById(id));
    	List<Category> categories= service.getAllData();
    	model.addAttribute("categories", categories);
		return "admin/UpdateProducts";
    	
    }
//    @PostMapping("/updateProduct")
//    public String UpdatesValue(@ModelAttribute Products products) {
//    	
//    	Pservice.SaveProduct(products);
//    	
//		return "redirect:/List_products";
//    	
//    }

}
