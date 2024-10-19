package com.example.client_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.client_app.model.Category;
import com.example.client_app.model.LoginResponse;
import com.example.client_app.model.Product;
import com.example.client_app.model.SellerModel;
import com.example.client_app.model.Size;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/v1")
public class AddProductController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/sizes")
	@ResponseBody
	public List<Size> getAllSizes(@RequestParam("categoryId") int categoryId) {
		List<Size> sizes = restTemplate.getForObject("http://localhost:8087/api/v1/sizes?categoryId= " + categoryId,
				List.class);
		return sizes;
	}

	@GetMapping("/addForm")
	public String showProductPage(Model model) {
		model.addAttribute("Product", new Product());
		List<Category> categories = restTemplate.getForObject("http://localhost:8087/api/v1/categories", List.class);
		model.addAttribute("categories", categories);
		return "addProductsToInventory";
	}

	@PostMapping("/add")
	public String addProductToInventory(@ModelAttribute Product product, @RequestParam("size") int sizeId,
			HttpSession session) {
		int sellerid = (Integer) session.getAttribute("sellerid");
		restTemplate.postForObject("http://localhost:8087/api/v1/add?sellerid=" + sellerid + "&sizeId=" + sizeId,
				product, String.class);
		return "redirect:/api/v1/seller/products";
	}

	@GetMapping("/update")
	public String showUpdateForm(@RequestParam("productId") int productId, Model model) {
		Product product = restTemplate.getForObject("http://localhost:8087/api/v1/update?productId=" + productId,
				Product.class);
		List<Category> categories = restTemplate.getForObject("http://localhost:8087/api/v1/categories", List.class);
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		return "updateProductToInventory";
	}

	@PostMapping("/update")
	public String updateProductToInventory(@ModelAttribute Product product, Model model,
			@RequestParam("productId") int productId, HttpSession session) {

		int sellerid = (Integer) session.getAttribute("sellerid");
		restTemplate.postForEntity(
				"http://localhost:8087/api/v1/update?sellerid=" + sellerid + "&productId=" + productId, product,
				String.class);

		return "redirect:/api/v1/seller/products";
	}

	@PostMapping("/delete")
	public String deleteProuctFromInventory(@RequestParam("productId") int productId, HttpSession session) {
		int sellerid = (Integer) session.getAttribute("sellerid");
		restTemplate.postForObject(
				"http://localhost:8087/api/v1/delete?sellerid=" + sellerid + "&productId=" + productId, null,
				String.class);
		if (sellerid == 0) {
			return "redirect:/api/v1/login";
		}
		return "redirect:/api/v1/seller/products";
	}

}