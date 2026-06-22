package com.ecommerce.adminController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.Repo.CategoryRepo;
import com.ecommerce.model.Category;
import com.ecommerce.services.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminHome {
	@Autowired
    private CategoryService service;
   
//	@Autowired
//	private CategoryRepo repos;
	
	  @GetMapping("/admin/Admindashboard")
	    public String admindash() {
	        return "/admin/Admindashboard";
	    }
    @GetMapping("/admin/addCategory")
    public String addcategory(Model model)
    {
    	model.addAttribute("Categories", service.getAllData());
        return "/admin/addCategory";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute Category category,
                               @RequestParam("file") MultipartFile file,
                               HttpSession session) throws IOException {

        session.removeAttribute("successMsg");
        session.removeAttribute("errorMsg");
        System.out.println("Value from form: " + category.isActive());
        String imageName = "default.jpg";

        // ✅ Step 1: Handle file upload FIRST
        if (file != null && !file.isEmpty()) {

            imageName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            String uploadDir = System.getProperty("user.dir") + "/uploads/category_img/";            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            Path path = Path.of(uploadDir + imageName);

            try {
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                session.setAttribute("errorMsg", "File upload failed");
                return "redirect:/admin/addCategory";
            }
        }

        // ✅ Step 2: Set image name
        category.setImageName(imageName);

        // ✅ Step 3: Check duplicate
        if (service.existsCategory(category.getName())) {
            session.setAttribute("errorMsg", "Category already exists");
            return "redirect:/admin/addCategory";
        }

        // ✅ Step 4: Save to DB
        Category saved = service.SaveCategory(category);

        if (saved == null) {
            session.setAttribute("errorMsg", "Not saved! Internal error");
        } else {
            session.setAttribute("successMsg", "Saved successfully");
        }

        return "redirect:/admin/addCategory";
    }
//        User submits form
//        ↓
//     Spring binds data → Category object
//        ↓
//     Check file uploaded
//        ↓
//     Generate unique filename
//        ↓
//     Save category in DB (with image name)
//        ↓
//     Save file in folder
//        ↓
//     Store success/error message
//        ↓
//     Redirect to page
    
    @GetMapping("/admin/updateCart/{id}")
    public String UpdateCarts(@PathVariable int id,  Model model ) {
    	model.addAttribute("category",service.getCById(id));
    	
		return "/admin/UpdateCart";
    	
    }
    
    // delete carts
    @GetMapping("/admin/delete/{id}")
    public String deleteData(@PathVariable int id) {
    	service.Catdelete(id);
		return "redirect:/admin/addCategory";
    }
}