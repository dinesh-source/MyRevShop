package com.revshop.cartservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.revshop.cartservice.entity.Cart;
import com.revshop.cartservice.service.CartService;

public class CartControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private CartService cartService;
	
	@InjectMocks
	private CartController cartController;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
	}
	
	@Test
	public void addCart() throws Exception{
		Cart cart = new Cart();
//		when(cartService.addToCart(any(Cart.class))).thenReturn(cart);
		
		mockMvc.perform(post("/api/v1/cart").flashAttr("cart", cart))
		.andExpect(status().isOk())
		.andExpect(view().name("showCart"));
	}
}
