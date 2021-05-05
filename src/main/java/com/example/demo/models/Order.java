package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.example.demo.models.validations.OrdersPostValidation;



@Entity(name = "ordertable")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message="oid must be provided", groups = OrdersPostValidation.class)
	private String oid;
	@NotNull(message="orderDate must be provided", groups = OrdersPostValidation.class)
	private String orderDate;
	@ManyToOne
	@NotNull(message="orderSalesperson must be provided", groups = OrdersPostValidation.class)
	@JoinColumn(name = "spid_FK")
	private Salesperson orderSalesperson;
	@ManyToOne
	@JoinColumn(name = "pid_FK")
	@NotNull(message="orderProduct must be provided", groups = OrdersPostValidation.class)
	private Product orderProduct;
	@NotNull(message="orderQuantity must be provided", groups = OrdersPostValidation.class)
	private int orderQuantity;
	public Order() {
		super();
	}
	public Order(String orderDate) {
		super();
		this.orderDate = orderDate;
	}
	public Order(String orderDate, Salesperson orderSalesperson) {
		super();
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
	}
	public Order(String orderDate, Salesperson orderSalesperson, Product orderProduct) {
		super();
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
		this.orderProduct = orderProduct;
	}
	public Order(String oid, String orderDate, Salesperson orderSalesperson) {
		super();
		this.oid = oid;
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
	}
	public Order(String oid, String orderDate, Salesperson orderSalesperson, Product orderProduct) {
		super();
		this.oid = oid;
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
		this.orderProduct = orderProduct;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public Salesperson getOrderSalesperson() {
		return orderSalesperson;
	}
	public void setOrderSalesperson(Salesperson orderSalesperson) {
		this.orderSalesperson = orderSalesperson;
	}
	public Product getorderProduct() {
		return orderProduct;
	}
	public void setOrderProduct(Product orderProduct) {
		this.orderProduct = orderProduct;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
}
