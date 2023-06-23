package com.suliman.datawarehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suliman.datawarehouse.entity.Deal;
import com.suliman.datawarehouse.service.DealService;

@RestController
@RequestMapping("/api")
public class DealController {

	private DealService dealService;

	@Autowired
	public DealController(DealService dealService) {
		this.dealService = dealService;
	}

	@GetMapping("/deal")
	public ResponseEntity<List<Deal>> getDeals() {
		return new ResponseEntity<>(dealService.getDeals(), HttpStatus.OK);
	}

	@GetMapping("/deal/{id}")
	public ResponseEntity<Deal> getDeal(@PathVariable Long id) {
		return new ResponseEntity<>(dealService.getDealById(id), HttpStatus.OK);
	}

	@PostMapping("/deal")
	public ResponseEntity<Deal> addDeal(@RequestBody Deal deal) {
		deal.setId(0L);
		return new ResponseEntity<>(dealService.saveDeal(deal), HttpStatus.OK);
	}

	@PutMapping("/deal")
	public ResponseEntity<Deal> updateDeal(@RequestBody Deal deal) {
		return new ResponseEntity<>(dealService.saveDeal(deal), HttpStatus.OK);
	}

	@DeleteMapping("/deal/{id}")
	public ResponseEntity<String> deleteDeal(@PathVariable Long id) {
		return new ResponseEntity<>(dealService.deleteDealById(id), HttpStatus.OK);
	}
}
