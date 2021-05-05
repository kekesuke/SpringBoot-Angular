package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Salesperson;

public interface SalespersonRepository extends CrudRepository<Salesperson, Integer> {
	public Optional<Salesperson> findBySpid(String sid);

}
