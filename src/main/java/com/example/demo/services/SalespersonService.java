package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Salesperson;
import com.example.demo.repositories.SalespersonRepository;

@Service
public class SalespersonService {
	@Autowired
	SalespersonRepository sr;

	public Iterable<Salesperson> getSalespeople() {
		return sr.findAll();
	}

	public Optional<Salesperson> getSalespersonSpid(String spid) throws SalespersonException {
		Optional<Salesperson> salespeople = sr.findBySpid(spid);
		if (salespeople.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales person " + spid + " not found");
		}
		return sr.findBySpid(spid);

	}

	public void deleteSalespersonBySpid(String spid) throws SalespersonException {
		Optional<Salesperson> salespeople = sr.findBySpid(spid);
		if (salespeople.isPresent()) {
			try {
				sr.delete(salespeople.get());
				;
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
						"salesperson " + spid + " can't be deleted. He/she has orders.”");
			}
		}

	}

	public void addSalesperson(Salesperson sp) throws SalespersonException {
		Iterable<Salesperson> salespeople = sr.findAll();
		for (Salesperson salesp : salespeople) {
			if (salesp.getSpid().equals(sp.getSpid())) {
				throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
						"Salesperson: " + sp.getSpid() + " already exists");
			}
		}

		sr.save(sp);

	}

	public void updateSalespersonBySpid(Salesperson sp, String spid) throws SalespersonException {
		Optional<Salesperson> salespeople = sr.findBySpid(spid);

		if (salespeople.isPresent()) {
			salespeople.get().setName(sp.getName());
			sr.save(salespeople.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Salesperson: " + spid + " not found");
		}

	}

}
