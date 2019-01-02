package com.niit.ZShop.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.niit.ZShopBackend.DAO.CategoryDAO;
import com.niit.ZShopBackend.DAO.ProductDAO;
import com.niit.ZShopBackend.model.Category;
import com.niit.ZShopBackend.model.Product;

@ControllerAdvice
public class GlobalController {
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	
	@ModelAttribute("categoryList")
	public List<Category> getAllCategory()
	{
		return categoryDAO.listCategories();
	}
	
	@ModelAttribute("todaysdeal")
	public List<Product> getProductsBycategory()
	{
		return productDAO.listProductByCategory(49);
	}
	
	@ModelAttribute("mostPopular")
	public List<Product> getProductsByPopularity()
	{
		return productDAO.listProductByCategory(50);
	}

}

//After login my category drop-down was not working thats why created global controller. now its working.