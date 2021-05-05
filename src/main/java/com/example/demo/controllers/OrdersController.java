package com.example.demo.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Order;
import com.example.demo.models.validations.OrdersPostValidation;
import com.example.demo.services.OrdersService;
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrdersController {
	
	@Autowired
	OrdersService os;
	
	@GetMapping(path = "/api/orders")
	public Iterable<Order> getAllOrders() {
		return os.getOrders();
	}
	
	@GetMapping("/api/specificOrders")
	public List<Order> getSalespersonBySpid(@RequestParam("year") int year, @RequestParam("qty") int qty) throws SalespersonException{
	    return os.findByOrderQuantityGreaterThanAndOrderDateContaining(year, qty);
	}
	
	@Validated(OrdersPostValidation.class)
	@PostMapping("/api/orders")
	public void addSalesperson(@Valid @RequestBody Order o) throws SalespersonException{
	   os.addOrder(o);
	}
	
}
