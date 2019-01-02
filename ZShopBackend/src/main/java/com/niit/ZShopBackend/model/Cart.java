package com.niit.ZShopBackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private double grandTotal;
	private int cartItem;
	@OneToOne
	private User userName;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public int getCartItem() {
		return cartItem;
	}
	public void setCartItem(int cartItem) {
		this.cartItem = cartItem;
	}
	public User getUserName() {
		return userName;
	}
	public void setUserName(User userName) {
		this.userName = userName;
	}
	
	
	
	
	

}
