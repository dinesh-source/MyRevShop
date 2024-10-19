package com.revshop.payment.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.revshop.payment.entity.Transaction;
import com.revshop.payment.repository.TransactionRepository;

import jakarta.annotation.PostConstruct;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Value("${razorpay.key.id}")
	private String razorpayId;
	
	@Value("${razorpay.key.secret}")
	private String razorpaySecret;
	
	private RazorpayClient razorpayCLient;
	
	@PostConstruct
	public void init() throws RazorpayException {
		this.razorpayCLient = new RazorpayClient(razorpayId, razorpaySecret);
	}
	
	public Transaction createOrder(Transaction order) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", order.getAmount() * 100); // amount in paise
        options.put("currency", "INR");
        options.put("receipt", order.getEmail());
        Order razorpayOrder = razorpayCLient.orders.create(options);
        if(razorpayOrder != null) {
	        order.setRazorpayOrderId(razorpayOrder.get("id"));
	        order.setOrderStatus(razorpayOrder.get("status"));
        }
        return transactionRepository.save(order);
    }

//	public Transaction updateStatus(Map<String, String> map) {
//    	String razorpayId = map.get("razorpay_order_id");
//    	Transaction order = transactionRepository.findByRazorpayOrderId(razorpayId);
//    	order.setOrderStatus("PAYMENT DONE");
//    	Transaction orders = transactionRepository.save(order);
//    	return orders;
//    }

}
