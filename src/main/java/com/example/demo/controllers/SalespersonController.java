package com.example.demo.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Salesperson;
import com.example.demo.models.validations.SalespersonPostValidation;
import com.example.demo.models.validations.SalespersonPutValidation;
import com.example.demo.services.SalespersonService;
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
public class SalespersonController {
	
	@Autowired
	SalespersonService ss;
	
	@GetMapping(path = "/api/salespeople")
	
	public Iterable<Salesperson> getSalesPeople() {
		return ss.getSalespeople();
	}
	
	@GetMapping("/api/salespeople/{spid}")
	public Optional<Salesperson> getSalespersonBySpid(@PathVariable String spid) throws SalespersonException{
	
	    return ss.getSalespersonSpid(spid);
	}
	
	@DeleteMapping("/api/salespeople/{spid}")
	public void deleteSalespersonBySpid(@PathVariable String spid) throws SalespersonException{
	   ss.deleteSalespersonBySpid(spid);
	}
	
	@Validated(SalespersonPostValidation.class)
	@PostMapping("/api/salespeople")
	public void addSalesperson(@Valid @RequestBody Salesperson sp) throws SalespersonException{
	   ss.addSalesperson(sp);
	}
	
	@Validated(SalespersonPutValidation.class)
	@PutMapping("/api/salespeople/{spid}")
	public void editSalesperson(@Valid @RequestBody Salesperson sp, @PathVariable String spid) throws SalespersonException{
	   ss.updateSalespersonBySpid(sp, spid);
	}
}
