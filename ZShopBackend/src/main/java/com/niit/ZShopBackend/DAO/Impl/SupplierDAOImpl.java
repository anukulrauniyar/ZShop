package com.niit.ZShopBackend.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.ZShopBackend.DAO.SupplierDAO;
import com.niit.ZShopBackend.model.Supplier;

@Repository("supplierDAO")
@Transactional
public class SupplierDAOImpl implements SupplierDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean saveSupplier(Supplier supplier) {
		try 
		{
			sessionFactory.getCurrentSession().save(supplier);
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateSupplier(Supplier supplier) {
		try 
		{
			sessionFactory.getCurrentSession().update(supplier);
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteSupplier(Supplier supplier) {
		try 
		{
			sessionFactory.getCurrentSession().delete(supplier);
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Supplier getSupplier(int supplierId) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Supplier supplier = session.get(Supplier.class, supplierId);
			return supplier;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Supplier> listSuppliers() {
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Supplier");
			List<Supplier> listSuppliers = query.list();
			session.close();
			return listSuppliers;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}

}
