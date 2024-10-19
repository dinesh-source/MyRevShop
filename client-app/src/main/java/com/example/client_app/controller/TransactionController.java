package com.example.client_app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/api/v1/orders")
public class TransactionController {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/transaction")
	public String ordersPage(@RequestParam("shippingAddress") String shippingAddress,
			@RequestParam("billingAddress") String billingAddress, @RequestParam("total") String total,
			Model model) {
		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("billingAddress", billingAddress);
		Double amount = Double.parseDouble(total);
		model.addAttribute("total", amount);
		return "transaction";
	}

	
	@PostMapping("/paymentCallback")
	public String paymentCallback(@RequestParam Map<String, String> response, @RequestParam("shippingAddress") String shippingAddress,
			@RequestParam("billingAddress") String billingAddress) {
//			Integer status = restTemplate.postForObject("http://localhost:8095/api/v1/orders/paymentCallback", response, Integer.class);
//			if(status == 200) {			
		return "redirect:/api/v1/orders/place?shippingAddress="+shippingAddress+"&billingAddress="+billingAddress;	
//			}
//			return "notfound";
	}

}
