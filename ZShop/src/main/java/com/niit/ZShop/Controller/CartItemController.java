package com.niit.ZShop.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.ZShopBackend.DAO.CartItemDAO;
import com.niit.ZShopBackend.DAO.ProductDAO;
import com.niit.ZShopBackend.model.CartItem;
import com.niit.ZShopBackend.model.Product;

@Controller
public class CartItemController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CartItemDAO cartItemDAO;
	
	@RequestMapping(value="/cartItem")
	public String showCart(HttpSession session,Model m)
	{
		String username = (String)session.getAttribute("username");
		List<CartItem> cartItems = cartItemDAO.listCartItems(username);  
		m.addAttribute("cartItems", cartItems);
		m.addAttribute("totalPurchaseAmount", this.calTotalPurchaseAmount(cartItems));
		
		return "CartItem";
	}
	
	@RequestMapping(value="/AddToCart/{productId}")
	public String addCart(@RequestParam("quantity")int quantity,@PathVariable("productId")int productId,HttpSession session,Model m)
	{
		Product product = productDAO.getProduct(productId);
		String username = (String)session.getAttribute("username");
		
		
		CartItem cartItem = new CartItem();
		cartItem.setProductId(product.getProductId());
		cartItem.setProductName(product.getProductName());
		cartItem.setProductCode(product.getProductCode());
		cartItem.setQuantity(quantity);
		cartItem.setBrand(product.getBrand());
		cartItem.setStatus("NA");
		cartItem.setUnitPrice(product.getUnitPrice());
		cartItem.setUserName(username);
		/*cartItem.setCartId(cartId);*/
		
		cartItemDAO.saveCartItem(cartItem);
		
		
		return "redirect:/cartItem";
	}
	
		@RequestMapping(value="/updateCartItem/{cartItemId}")
		public String updateCart(@RequestParam("quantity") int quantity,@PathVariable("cartItemId")int cartItemId,HttpSession session,Model m)
		{
			CartItem cartItem = cartItemDAO.getCartItem(cartItemId);
			String username = (String)session.getAttribute("username");
			cartItem.setQuantity(quantity);
			cartItemDAO.updateCartItem(cartItem);
			List<CartItem> cartItems = cartItemDAO.listCartItems(username);  
			m.addAttribute("cartItems",cartItems);
			m.addAttribute("totalPurchaseAmount", this.calTotalPurchaseAmount(cartItems));
			return "CartItem";
		}
		
		@RequestMapping(value="/deleteCartItem/{cartItemId}")
		public String deleteCart(@PathVariable("cartItemId") int cartItemId,HttpSession session,Model m)
		{
			CartItem cartItem = cartItemDAO.getCartItem(cartItemId);
			String username = (String) session.getAttribute("username");
			
			cartItemDAO.deleteCartItem(cartItem);
			List<CartItem> cartItems = cartItemDAO.listCartItems(username);  
			
			m.addAttribute("cartItems", cartItems);
			m.addAttribute("totalPurchaseAmount", this.calTotalPurchaseAmount(cartItems));
			
			return "CartItem";
		}
		
		@RequestMapping(value="/continueShopping")
		public String continueShopping()
		{
			
			return"redirect:/home";
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
