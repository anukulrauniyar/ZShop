package com.niit.ZShopBackend.DAO;

import com.niit.ZShopBackend.model.OrderDetail;

public interface OrderDetailDAO {
	
	public boolean addOrderDetail(OrderDetail orderDetail);
	public boolean updateOrderDetail(String userName);
	public boolean deleteOrderDetail(OrderDetail orderDetail);
	
	

}
