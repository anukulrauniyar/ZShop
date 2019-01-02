package com.niit.ZShop.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.ZShopBackend.DAO.CartItemDAO;
import com.niit.ZShopBackend.model.CartItem;

@Controller
public class OrderController {
	
	@Autowired
	private CartItemDAO cartItemDAO; 
	
	@RequestMapping(value="/checkOut")
	public String checkOutProcess(Model m, HttpSession session)
	{
		String username = (String)session.getAttribute("username");
		List<CartItem> cartItems = cartItemDAO.listCartItems(username);  
		m.addAttribute("cartItems", cartItems);
		m.addAttribute("totalPurchaseAmount", this.calTotalPurchaseAmount(cartItems));
		return"MyOrder";
	}
	
	@RequestMapping(value="/payment")
	public String paymentProcess(Model m,HttpSession session)
	{
		String username = (String)session.getAttribute("username");
		List<CartItem> cartItems = cartItemDAO.listCartItems(username);  
		m.addAttribute("cartItems", cartItems);
		m.addAttribute("totalPurchaseAmount", this.calTotalPurchaseAmount(cartItems));
		return "Payment";
	}
	
	public double calTotalPurchaseAmount(List<CartItem> cartItems)
	{
		double totalPurchaseAmount = 0;
		int count = 0;
		while(count<cartItems.size())
			{
				CartItem cartItem = cartItems.get(count);
				totalPurchaseAmount= totalPurchaseAmount + (cartItem.getQuantity()*cartItem.getUnitPrice());
				count++;
			}
			
			return totalPurchaseAmount;
	}

}
