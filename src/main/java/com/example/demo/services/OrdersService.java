package com.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Order;
import com.example.demo.repositories.OrdersRepository;

@Service
public class OrdersService {
	@Autowired
	OrdersRepository or;

	public Iterable<Order> getOrders() {
		return or.findAll();
	}

	public List<Order> findByOrderQuantityGreaterThanAndOrderDateContaining(int orderDate, int orderQuantity){
		return or.findByOrderQuantityGreaterThanAndOrderDateContaining(orderQuantity, Integer.toString(orderDate));
		
	}
	
	public void addOrder(Order order) throws SalespersonException {
	Iterable<Order> orders = or.findAll();
	boolean spidNotfound = true;
	boolean pidNotfound = true;
	boolean pidNotOrderable = false;
	boolean quantityOrderable = false;
	int  quantity = 0;
	
	for (Order o : orders) {
		if (o.getOrderSalesperson().getSpid().equals(order.getOrderSalesperson().getSpid())) {
			spidNotfound = false;
		}
		if (o.getorderProduct().getPid().equals(order.getorderProduct().getPid())) {
			pidNotfound = false;
		}
		if (o.getorderProduct().getPid().equals(order.getorderProduct().getPid()) && o.getorderProduct().getOrderable().equals("false")) {
				pidNotOrderable = true;
		}
		if (o.getorderProduct().getPid().equals(order.getorderProduct().getPid()) && o.getorderProduct().getOrderable().equals("true")) {
			if(o.getorderProduct().getQuantity() < order.getOrderQuantity()) {
				quantityOrderable = true;
				quantity = o.getorderProduct().getQuantity();
			}
		}
		
	}
	
	if(spidNotfound){
		throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Salesperson " +order.getOrderSalesperson().getSpid() +" does not exist");
	}else if(pidNotfound) {
		throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Product " +order.getorderProduct().getPid() +" does not exist");
	}else if(pidNotOrderable) {
		throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Product " +order.getorderProduct().getPid() +" is not ordarable");
	}else if(quantityOrderable) {
		throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Stock on hand " +quantity +" is less than order quantity of " +order.getOrderQuantity());
	}
	
	
	for (Order o : orders) {
		if(o.getorderProduct().getPid().equals(order.getorderProduct().getPid())) {
			o.getorderProduct().setQuantity(o.getorderProduct().getQuantity() - order.getOrderQuantity());
			or.save(o);
			return;
		}

		
	}

	

}
	

}
