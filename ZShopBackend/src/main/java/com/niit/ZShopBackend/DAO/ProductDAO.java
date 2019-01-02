package com.niit.ZShopBackend.DAO;

import java.util.List;

import com.niit.ZShopBackend.model.Product;

public interface ProductDAO {
	
	public boolean saveProduct(Product product);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(Product product);
	public Product getProduct(int productId);
	public List<Product> listProducts();
	public List<Product> listProductByCategory(int categoryId);
	public List<Product> listProductBySupplier(int supplierId);
	public List<Product> listProductBySearch(String search);
	public List<Product> listProductByFilter(int MIN,int MAX);
	public List<Product> getListByBrand(String brand);

}
