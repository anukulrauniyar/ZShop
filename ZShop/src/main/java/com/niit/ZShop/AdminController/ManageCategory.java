package com.niit.ZShop.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.ZShopBackend.DAO.CategoryDAO;
import com.niit.ZShopBackend.model.Category;

@Controller
public class ManageCategory {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	boolean flag = false;

	@RequestMapping(value="/category")
	public String showCategory(Model m)
	{
		flag = false;
		
		List<Category> listCategories = categoryDAO.listCategories();
		m.addAttribute("categoryList", listCategories);
		m.addAttribute("flag", flag);
		return "ManageCategory";
	}
	
	@RequestMapping(value="/InserCategory", method=RequestMethod.POST)
	public String insertCategory(@RequestParam("categoryName")String categoryName,@RequestParam("description")String description,Model m)
	{
		flag = false;
		
		Category category = new Category();
		category.setCategoryName(categoryName);
		category.setDescription(description);
		categoryDAO.saveCategory(category);
		
		List<Category> listCategories = categoryDAO.listCategories(); //again using these 2 lines bcz I hv to retrieve data that I hv inserted in database.
		m.addAttribute("categoryList", listCategories);
		m.addAttribute("flag", flag);
		return "ManageCategory";
	}
	
	@RequestMapping(value="/deleteCategory/{categoryId}")
	public String deleteCategory(@PathVariable("categoryId") int categoryId,Model m)
	{
		flag = false;
		
		Category category = categoryDAO.getCategory(categoryId);
		categoryDAO.deleteCategory(category);
		
		List<Category> listCategories = categoryDAO.listCategories();
		m.addAttribute("categoryList", listCategories);
		m.addAttribute("flag", flag);
		return"ManageCategory";
	}
	
	@RequestMapping(value="/editCategory/{categoryId}")
	public String editCategory(@PathVariable("categoryId") int categoryId, Model m)
	{
		flag = true;
		
		Category category = categoryDAO.getCategory(categoryId);
		m.addAttribute("categoryData", category);
		m.addAttribute("flag", flag);
		return"ManageCategory";
	}
	
	@RequestMapping(value="/updateCategory", method=RequestMethod.POST)
	public String updateCategory(@RequestParam("categoryId") int categoryId,@RequestParam("categoryName")String categoryName
			,@RequestParam("description")String description,Model m)
	{
		flag = false;
		
		Category category = categoryDAO.getCategory(categoryId);
		category.setCategoryName(categoryName);
		category.setDescription(description);
		categoryDAO.updateCategory(category);
		
		List<Category> listCategories = categoryDAO.listCategories(); //again using these 2 lines bcz I hv to retrieve data that I hv inserted in database.
		m.addAttribute("categoryList", listCategories);
		m.addAttribute("flag", flag);
		return "ManageCategory";
	}
	
}
