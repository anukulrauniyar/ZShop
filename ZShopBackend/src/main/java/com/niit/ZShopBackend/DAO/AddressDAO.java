package com.niit.ZShopBackend.DAO;

import java.util.List;

import com.niit.ZShopBackend.model.Address;

public interface AddressDAO {
	
	public boolean saveAddress(Address address);
	public boolean updateAddress(Address address);
	public boolean deleteAddress(Address address);
	public Address getAddress(int addressId);
	public List<Address> listAddress(String userName);

}
