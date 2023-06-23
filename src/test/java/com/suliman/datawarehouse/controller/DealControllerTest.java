package com.suliman.datawarehouse.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suliman.datawarehouse.entity.Deal;
import com.suliman.datawarehouse.exception.NoSuchDealException;

@SpringBootTest
@AutoConfigureMockMvc
public class DealControllerTest {

	private MockMvc mockMvc;

	private ObjectMapper mapper;

	@Autowired
	public DealControllerTest(MockMvc mockMvc, ObjectMapper mapper) {
		this.mockMvc = mockMvc;
		this.mapper = mapper;
	}

	@Test
	public void assertThatGetDealByIdReturnsObject() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/deal/{id}", 3).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists()).andReturn();

	}

	@Test
	public void assertThatGetDealByIdThrowsNoSuchDealException() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/deal/{id}", 999).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andExpect(result -> {
					Throwable throwable = result.getResolvedException();
					if (throwable == null) {
						throw new AssertionError("Expected exception not thrown");
					}
					assertThat(throwable.getClass().equals(NoSuchDealException.class));
				}).andReturn();

	}

	@Test
	public void assertThatGetDealReturnsObjectList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/deal").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists()).andReturn();

	}

	@Test
	public void assertThatGetDealByIdThrowsMethodArgumentTypeMismatchException() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/deal/{id}", "ABC").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(result -> {
					Throwable throwable = result.getResolvedException();
					if (throwable == null) {
						throw new AssertionError("Expected exception not thrown");
					}
					assertThat(throwable.getClass().equals(MethodArgumentTypeMismatchException.class));
				}).andReturn();

	}

	@Test
	public void assertThatPostDealReturnsNewDeal() throws Exception {
		Deal deal = new Deal("JOD", "USD", LocalDateTime.now(), new BigDecimal(450.45));
		mockMvc.perform(MockMvcRequestBuilders.post("/api/deal").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(deal))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fromCurrency").value("JOD")).andReturn();

	}

	@Test
	public void assertThatPostDealReturnsHttpRequestMethodNotSupportedExceptionWhenPostingToInvalidURI()
			throws Exception {
		Deal deal = new Deal("JOD", "USD", LocalDateTime.now(), new BigDecimal(450.45));
		mockMvc.perform(MockMvcRequestBuilders.post("/api/deal/something").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(deal)))
				.andExpect(MockMvcResultMatchers.status().isMethodNotAllowed()).andExpect(result -> {
					Throwable throwable = result.getResolvedException();
					if (throwable == null) {
						throw new AssertionError("Expected exception not thrown");
					}
					assertThat(throwable.getClass().equals(HttpRequestMethodNotSupportedException.class));
				}).andReturn();

	}

	@Test
	public void assertThatPutDealReturnsNewDeal() throws Exception {
		Deal deal = new Deal("JOD", "USD", LocalDateTime.now(), new BigDecimal(450.45));
		deal.setId(1L);
		mockMvc.perform(MockMvcRequestBuilders.put("/api/deal").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(deal))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.fromCurrency").value("JOD")).andReturn();

	}

	@Test
	public void assertThatPutDealReturnsHttpRequestMethodNotSupportedExceptionWhenPostingToInvalidURI()
			throws Exception {
		Deal deal = new Deal("JOD", "USD", LocalDateTime.now(), new BigDecimal(450.45));
		deal.setId(1L);
		mockMvc.perform(MockMvcRequestBuilders.put("/api/deal/something").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(deal)))
				.andExpect(MockMvcResultMatchers.status().isMethodNotAllowed()).andExpect(result -> {
					Throwable throwable = result.getResolvedException();
					if (throwable == null) {
						throw new AssertionError("Expected exception not thrown");
					}
					assertThat(throwable.getClass().equals(HttpRequestMethodNotSupportedException.class));
				}).andReturn();

	}

	@Test
	public void assertThatDeleteDealByIdReturnsString() throws Exception {
		Long dealId = 5L;
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/deal/{id}", dealId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Deleted Deal with id: " + dealId)).andReturn();
	}

	@Test
	public void assertThatDeleteDealByIdThrowsNoSuchDealException() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/deal/{id}", 999).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andExpect(result -> {
					Throwable throwable = result.getResolvedException();
					if (throwable == null) {
						throw new AssertionError("Expected exception not thrown");
					}
					assertThat(throwable.getClass().equals(NoSuchDealException.class));
				}).andReturn();

	}

}
