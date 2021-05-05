package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Order;
import com.example.demo.models.Product;


public interface OrdersRepository extends CrudRepository<Order, Integer> {
	public List<Order> findByOrderQuantityGreaterThanAndOrderDateContaining(int orderQuantity, String orderDate);
	public Optional<Order> findByorderProduct(Product orderProduct);

}
