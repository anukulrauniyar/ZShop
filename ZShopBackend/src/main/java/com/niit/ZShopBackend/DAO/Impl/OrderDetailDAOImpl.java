package com.niit.ZShopBackend.DAO.Impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.ZShopBackend.DAO.OrderDetailDAO;
import com.niit.ZShopBackend.model.OrderDetail;


@Repository("orderDetailDAO")
@Transactional
public class OrderDetailDAOImpl implements OrderDetailDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addOrderDetail(OrderDetail orderDetail) {
		try 
		{
			sessionFactory.getCurrentSession().save(orderDetail);
			return true;
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateOrderDetail(String userName) {
		try 
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("update CartItem set status='Purchase' where userName=:myusername and status='NA' ");
			query.setParameter("myusername", userName);
			int row_eff = query.executeUpdate();
			if(row_eff>0)
				return true;
			else
				return false;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteOrderDetail(OrderDetail orderDetail) {
		try 
		{
			sessionFactory.getCurrentSession().delete(orderDetail);
			return true;
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	

}
