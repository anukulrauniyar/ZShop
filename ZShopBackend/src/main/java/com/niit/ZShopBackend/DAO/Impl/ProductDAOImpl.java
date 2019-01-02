package com.niit.ZShopBackend.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.ZShopBackend.DAO.ProductDAO;
import com.niit.ZShopBackend.model.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean saveProduct(Product product) {
		try
		{
			sessionFactory.getCurrentSession().save(product);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateProduct(Product product) {
		try
		{
			sessionFactory.getCurrentSession().update(product);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteProduct(Product product) {
		try
		{
			sessionFactory.getCurrentSession().delete(product);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Product getProduct(int productId) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Product product = session.get(Product.class, productId);
			return product;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> listProducts() {
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Product");
			List<Product> listProducts = query.list();
			session.close();
			return listProducts;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> listProductByCategory(int categoryId) {
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Product where categoryId=:categoryId");
			query.setParameter("categoryId", categoryId);
			List<Product> listProducts = query.list();
			session.close();
			return listProducts;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> listProductBySupplier(int supplierId) {
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Product where supplierId=:supplierId");
			query.setParameter("supplierId", supplierId);
			List<Product> listProducts = query.list();
			session.close();
			return listProducts;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> listProductBySearch(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> listProductByFilter(int MIN, int MAX) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getListByBrand(String brand) {
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Product where brand=:brand");
			query.setParameter("brand", brand);
			List<Product> listProducts = query.list();
			session.close();
			return listProducts;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
