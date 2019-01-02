package com.niit.ZShop.AdminController;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.ZShopBackend.DAO.CategoryDAO;
import com.niit.ZShopBackend.DAO.SupplierDAO;
import com.niit.ZShopBackend.model.Category;
import com.niit.ZShopBackend.model.Supplier;

@Controller
public class ManageSupplier {

	@Autowired
	private SupplierDAO supplierDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value="/supplier")
	public String showSupplier(Model m)
	{
		Supplier supplier = new Supplier();
		m.addAttribute(supplier);
		m.addAttribute("categoryList", this.getCategories());
		m.addAttribute("supplierList", supplierDAO.listSuppliers());
		return "ManageSupplier";
	}
	
	@RequestMapping(value="/SupplierInsert", method = RequestMethod.POST)
	public String insertSupplier(@ModelAttribute("supplier")Supplier supplier,Model m)
	{
		supplierDAO.saveSupplier(supplier);
		Supplier supplier1= new Supplier();
		m.addAttribute(supplier1);
		
		m.addAttribute("categoryList", this.getCategories());
		m.addAttribute("supplierList", supplierDAO.listSuppliers());
		return"ManageSupplier";
	}
	
		@RequestMapping(value="deleteSupplier/{supplierId}")
		public String deleteSupplier(@PathVariable("supplierId") int supplierId,Model m)
		{
			Supplier supplier = supplierDAO.getSupplier(supplierId);
			supplierDAO.deleteSupplier(supplier);
			Supplier supplier1= new Supplier();
			m.addAttribute(supplier1);
			
			m.addAttribute("categoryList", this.getCategories());
			m.addAttribute("supplierList", supplierDAO.listSuppliers());
			return "ManageSupplier";
		}
		
		@RequestMapping(value="/editSupplier/{supplierId}")
		public String editSupplier(@PathVariable("supplierId") int supplierId,Model m)
		{
			Supplier supplier = supplierDAO.getSupplier(supplierId);
			m.addAttribute(supplier);
			m.addAttribute("categoryList", this.getCategories());
			m.addAttribute("supplierList", supplierDAO.listSuppliers());
			return "UpdateSupplier";
		}
		
		@RequestMapping(value="/UpdateSupplier", method=RequestMethod.POST)
		public String updateSupplier(@ModelAttribute("supplier")Supplier supplier,Model m)
		{
			supplierDAO.updateSupplier(supplier);
			Supplier supplier1= new Supplier();
			m.addAttribute(supplier1);
			m.addAttribute("categoryList", this.getCategories());
			m.addAttribute("supplierList", supplierDAO.listSuppliers());
			return "ManageSupplier";
		}
	
	public LinkedHashMap<Integer,String> getCategories()
	{
		List<Category> listCategories = categoryDAO.listCategories();
		LinkedHashMap<Integer,String> categoryData = new LinkedHashMap<Integer,String>();
		for(Category category:listCategories)
		{
			categoryData.put(category.getCategoryId(), category.getCategoryName());
		}
			return categoryData;
	}
	
}
