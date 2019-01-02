package com.niit.ZShopBackend.DAO;

import java.util.List;

import com.niit.ZShopBackend.model.User;

public interface UserDAO {
	
	public boolean saveUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(User user);
	public User getUser(String userName);
	public User getUserByEmailId(String emailId);
	public List<User> listUsers();

}
