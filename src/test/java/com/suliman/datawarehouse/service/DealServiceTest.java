package com.suliman.datawarehouse.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.suliman.datawarehouse.entity.Deal;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
@AutoConfigureMockMvc
public class DealServiceTest {

	private DealService dealService;

	@Autowired
	public DealServiceTest(DealService dealService) {
		this.dealService = dealService;
	}

	@Test
	public void assertThatPostingWithInvalidConstraintDataThrowsConstraintViolationException() throws Exception {
		Deal deal = new Deal("J", "U", LocalDateTime.now(), new BigDecimal(450.45));

		assertThrows(ConstraintViolationException.class, () -> dealService.saveDeal(deal));

	}

}
