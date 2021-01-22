package com.raihan.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raihan.test.model.Shopping;
import com.raihan.test.service.ShoppingService;

@RestController
@RequestMapping("/api")
public class ShoppingController {
	
	@Autowired
	private ShoppingService shoppingService;
	
	@PostMapping("/shopping")
	public Map create(@RequestBody Shopping shopping) {
		Shopping newData = shoppingService.save(shopping);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("shopping", newData);
		return response;
	}
	
	@GetMapping("/shopping")
	public Map getAllData() {
		List<Shopping> data = shoppingService.getAll();
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("shopping", data);
		return response;
	}
	
	@GetMapping("/shopping/{id}")
	public Shopping getShopping(@PathVariable Long id) {
		return shoppingService.getById(id);
	}
	
	@DeleteMapping("/shopping/{id}")
	public Map deleteShopping(@PathVariable Long id) {
		Shopping shopping = shoppingService.getById(id);
		Map<String, Object> response = new HashMap<String, Object>();
		if (shopping == null) {
			response.put("message", "Fail to delete");
			return response;
		}
		shoppingService.delete(shopping);
		response.put("message", "Sukses");
		return response;
	}
	
	@PutMapping("/shopping/{id}")
	public Map updateShopping(@RequestBody Shopping shopping, @PathVariable Long id) {
		Shopping data = shoppingService.getById(id);
		Map<String, Object> response = new HashMap<String, Object>();
		if (shopping == null) {
			response.put("message", "Fail to delete");
			return response;
		}
		data.setName(shopping.getName());
		data.setCreateddate(shopping.getCreateddate());
		Shopping newData = shoppingService.save(data);
		response.put("message", "Sukses");
		return response;
	}
	
	
	
}
