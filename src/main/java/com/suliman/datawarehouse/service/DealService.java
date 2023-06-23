package com.suliman.datawarehouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suliman.datawarehouse.entity.Deal;
import com.suliman.datawarehouse.exception.NoSuchDealException;
import com.suliman.datawarehouse.repo.DealRepository;

@Service("DealService")
public class DealService {

	private Deal deal;
	private DealRepository dealRepository;

	@Autowired
	public DealService(DealRepository dealRepository) {
		this.dealRepository = dealRepository;
	}

	public Deal getDealById(Long id) {
		Optional<Deal> dealOptional = dealRepository.findById(id);

		if (dealOptional.isEmpty()) {
			throw new NoSuchDealException("Deal not found with id: " + id);
		}

		return dealOptional.get();
	}

	public List<Deal> getDeals() {
		return dealRepository.findAll();
	}

	public Deal saveDeal(Deal deal) {
		return dealRepository.save(deal);
	}

	public String deleteDealById(Long id) {
		deal = getDealById(id);
		dealRepository.delete(deal);
		return "Deleted Deal with id: " + id;
	}

}
