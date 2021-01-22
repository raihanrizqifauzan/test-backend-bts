package com.raihan.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raihan.test.model.Shopping;
import com.raihan.test.repository.ShoppingRepository;

@Service
public class ShoppingService {
	
	@Autowired
	private ShoppingRepository shoppingRepository;
	
	public Shopping save(Shopping shopping) {
		return shoppingRepository.save(shopping);
	}
	
	public List<Shopping> getAll() {
		return shoppingRepository.findAll();
	}
	
	public Shopping getById(Long id) {
		return shoppingRepository.findById(id).orElse(null);
	}
	
	public void delete(Shopping shopping) {
		shoppingRepository.delete(shopping);
	}
}
