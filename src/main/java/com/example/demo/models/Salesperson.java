package com.example.demo.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.example.demo.models.validations.SalespersonPostValidation;
import com.example.demo.models.validations.SalespersonPutValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Salesperson {
	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message="SPID must be provided", groups = SalespersonPostValidation.class)
	@Null(message="SPID  must not be provided", groups = SalespersonPutValidation.class)
	private String spid;
	@NotNull(message="Name must be provided", groups = SalespersonPostValidation.class)
	@NotNull(message="Name must be provided", groups = SalespersonPutValidation.class)
	private String name;
	@OneToMany(mappedBy = "orderSalesperson")
	@JsonIgnore
	private List<Order> salespersonOrders;
	public Salesperson() {
		super();
	}
	public Salesperson(String name) {
		super();
		this.name = name;
	}
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Order> getSalespersonOrders() {
		return salespersonOrders;
	}
	public void SalespersonOrders(List<Order> salespersonOrders) {
		this.salespersonOrders = salespersonOrders;
	}
	
	
}
