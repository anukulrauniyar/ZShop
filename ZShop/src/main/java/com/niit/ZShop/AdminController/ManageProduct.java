package com.niit.ZShop.AdminController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.niit.ZShopBackend.DAO.CategoryDAO;
import com.niit.ZShopBackend.DAO.ProductDAO;
import com.niit.ZShopBackend.DAO.SupplierDAO;
import com.niit.ZShopBackend.model.Category;
import com.niit.ZShopBackend.model.Product;
import com.niit.ZShopBackend.model.Supplier;

@Controller
public class ManageProduct {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private SupplierDAO supplierDAO;
	
	@RequestMapping(value="/product")
	public String showProduct(Model m)
	{
		Product product = new Product();
		m.addAttribute(product);
		m.addAttribute("categoryList",this.getCategories());
		m.addAttribute("supplierList", this.getSuppliers());
		m.addAttribute("productList",productDAO.listProducts());
		return "ManageProduct";
	}
	
	@RequestMapping(value="/totalProductInfo/{productId}")
	public String singleProductDisplay(@PathVariable("productId") int productId,Model m)
	{
		Product product = productDAO.getProduct(productId);
		m.addAttribute("product",product);
		m.addAttribute("categoryName",categoryDAO.getCategory(product.getCategoryId()).getCategoryName());
		return "SingleProduct";
	}
	
	@RequestMapping(value="/displayProduct")
	public String displayProduct(Model m)
	{
		m.addAttribute("productList",productDAO.listProducts());
		m.addAttribute("categoryList",this.getCategories());
		m.addAttribute("supplierList", this.getSuppliers());
		return "DisplayProduct";
	}
	
	
	@RequestMapping(value="/product/brand/{brand}")
	public String displayByBrnadProduct(@PathVariable("brand")String brand,Model m)
	{
		List<Product> prodlist=productDAO.getListByBrand(brand);
		m.addAttribute("productList",prodlist);
		m.addAttribute("categoryList",this.getCategories());
		m.addAttribute("supplierList", this.getSuppliers());
		return "DisplayProduct";
	}
	
	
	
	
	
	
	@RequestMapping(value="/ProductInsert",method = RequestMethod.POST)
	public String insertProduct(@ModelAttribute("product") Product product,@RequestParam("file")MultipartFile imageFile,Model m)
	{
		productDAO.saveProduct(product);
		Product product1 = new Product();
		m.addAttribute(product1);
		
		// Multipart file uploading
					String path="C:\\Users\\Srivastava\\eclipse-workspace\\ZShop\\src\\main\\webapp\\resources\\images\\";
					path = path+String.valueOf(product.getProductId())+".jpg";
					File file = new File(path);
					if(!imageFile.isEmpty())
					{
						try
						{
							byte[] buffer= imageFile.getBytes();
							FileOutputStream fos= new FileOutputStream(file);
							BufferedOutputStream bos = new BufferedOutputStream(fos);
							bos.write(buffer);
							bos.close();
						}
						catch (Exception e) 
						{
							System.out.println("Exception Arised:"+e);
						}
					}
					else
					{
						m.addAttribute("ErrorInfo", "Image can't upload");
					}
		
		m.addAttribute("categoryList",this.getCategories());
		m.addAttribute("supplierList", this.getSuppliers());
		m.addAttribute("productList",productDAO.listProducts());
		return "ManageProduct";
	}
	
		@RequestMapping(value="/deleteProduct/{productId}")
		public String deleteProduct(@PathVariable("productId") int productId, Model m)
		{
			Product product = productDAO.getProduct(productId);
			productDAO.deleteProduct(product);
			Product product1 = new Product();
			m.addAttribute(product1);
			
			m.addAttribute("categoryList",this.getCategories());
			m.addAttribute("supplierList", this.getSuppliers());
			m.addAttribute("productList",productDAO.listProducts());
			return "ManageProduct";
		}
		
		@RequestMapping(value="/editProduct/{productId}")
		public String editProduct(@PathVariable("productId") int productId,Model m)
		{
			Product product = productDAO.getProduct(productId);
			m.addAttribute(product);
			m.addAttribute("categoryList",this.getCategories());
			m.addAttribute("supplierList", this.getSuppliers());
			m.addAttribute("productList",productDAO.listProducts());
			return "UpdateProduct";
		}
		
		@RequestMapping(value="/UpdateProduct", method=RequestMethod.POST)
		public String updateProduct(@ModelAttribute("product") Product product,Model m)
		{
			productDAO.updateProduct(product);
			Product product1 = new Product();
			m.addAttribute(product1);
			m.addAttribute("categoryList",this.getCategories());
			m.addAttribute("supplierList", this.getSuppliers());
			m.addAttribute("productList",productDAO.listProducts());
			return "ManageProduct";
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
		
		public LinkedHashMap<Integer,String> getSuppliers()
		{
			List<Supplier> listSuppliers = supplierDAO.listSuppliers();
			LinkedHashMap<Integer,String> supplierData = new LinkedHashMap<Integer,String>();
			for(Supplier supplier: listSuppliers)
			{
				supplierData.put(supplier.getSupplierId(), supplier.getSupplierName());
			}
				return supplierData;
		}
		
		
		
		
}
