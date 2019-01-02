package com.niit.ZShopBackend.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.ZShopBackend.DAO.CartItemDAO;
import com.niit.ZShopBackend.model.CartItem;

@Repository("cartItemDAO")
@Transactional
public class CartItemDAOImpl implements CartItemDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveCartItem(CartItem cartItem) {
		try
		{
			sessionFactory.getCurrentSession().save(cartItem);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCartItem(CartItem cartItem) {
		try
		{
			sessionFactory.getCurrentSession().update(cartItem);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCartItem(CartItem cartItem) {
		try
		{
			sessionFactory.getCurrentSession().delete(cartItem);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CartItem getCartItem(int cartItemId) {
		try
		{
			Session session = sessionFactory.openSession();
			CartItem cartItem =(CartItem) session.get(CartItem.class, cartItemId);
			session.close();
			return cartItem;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CartItem> listCartItems(String userName) {
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from CartItem where userName=:myusername and status='NA'");
			query.setParameter("myusername", userName);
			List<CartItem> listCartItems = (List<CartItem>)query.list();
			session.close();
			return listCartItems;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	

	
}
