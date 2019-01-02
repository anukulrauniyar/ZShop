package com.niit.ZShopBackend.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.ZShopBackend.DAO.AddressDAO;
import com.niit.ZShopBackend.model.Address;

@Repository("addressDAO")
@Transactional
public class AddressDAOImpl implements AddressDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean saveAddress(Address address) {
		try
		{
			sessionFactory.getCurrentSession().save(address);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateAddress(Address address) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAddress(Address address) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Address getAddress(int addressId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> listAddress(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
