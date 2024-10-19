package com.revshop.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.productservice.entity.Category;
import com.revshop.productservice.entity.Product;
import com.revshop.productservice.entity.SellerModel;
import com.revshop.productservice.entity.Size;
import com.revshop.productservice.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class AddProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/sizes")
	@ResponseBody
	public ResponseEntity<?> getAllSizes(@RequestParam("categoryId") int categoryId) {
		List<Size> sizes = productService.getAllSizes(categoryId);
		return ResponseEntity.ok(sizes);
	}


	@GetMapping("/addForm")
	public ResponseEntity<?> showProductPage() {
		List<Category> categories = productService.getAllCategories();
		return ResponseEntity.ok(categories);
	}


//
	@PostMapping("/add")
	public ResponseEntity<String> addProductToInventory(@RequestParam("sizeId") int sizeId,
			@RequestParam("sellerid") int sellerid, @RequestBody Product product) {
		Size size = productService.getSizeById(sizeId);
		product.setSize(size);
		if (sellerid == 0) {
			return ResponseEntity.ok("Login");
		}
		SellerModel sellerModel = productService.getSellerById(sellerid);
		product.setSellerModel(sellerModel);
		productService.saveProduct(product);
		return ResponseEntity.ok("Added product");
	}

	@PostMapping("/delete")
	public ResponseEntity<String> deleteProuctFromInventory(@RequestParam("productId") int productId,
			@RequestParam("sellerid") int sellerid) {
		SellerModel sellerModel = productService.getSellerById(sellerid);
		if (sellerModel == null) {
			return ResponseEntity.ok("Login");
		}
		productService.deleteProduct(productId);
		return ResponseEntity.ok("delete Product");
	}

	@PostMapping("/update")
	public ResponseEntity<String> updateProductToInventory(@RequestBody Product product,
			@RequestParam("sellerid") int sellerid, @RequestParam("productId") int productId) {
		product.setProductId(productId);
		SellerModel sellerModel = productService.getSellerById(sellerid);
		product.setSellerModel(sellerModel);
		Product p = productService.findById(productId);
		product.setSize(p.getSize());
		productService.saveProduct(product);
		return ResponseEntity.ok("product Updated");
	}

	@GetMapping("/update")
	public ResponseEntity<Product> showUpdateForm(@RequestParam("productId") int productId) {
		Product product = productService.getProductDetails(productId);
		return ResponseEntity.ok(product);
	}
}