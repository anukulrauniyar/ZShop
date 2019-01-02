package com.niit.ZShopBackend.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.ZShopBackend.DAO.UserDAO;
import com.niit.ZShopBackend.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean saveUser(User user) {
		try
		{
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		try
		{
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		try
		{
			sessionFactory.getCurrentSession().delete(user);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUser(String userName) {
		try
		{
			Session session = sessionFactory.getCurrentSession();
			User user = session.get(User.class, userName);
			return user;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> listUsers() {
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from User");
			List<User> listUsers = query.list();
			session.close();
			return listUsers;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getUserByEmailId(String emailId) {
		String selectQuery = "FROM User WHERE emailId = :emailId";
		try {
		return sessionFactory.getCurrentSession().createQuery(selectQuery,User.class)
						.setParameter("emailId",emailId)
							.getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
	}

}
