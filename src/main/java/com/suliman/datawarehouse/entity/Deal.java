package com.suliman.datawarehouse.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "deal")
public class Deal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty
	@Column(name = "from_currency")
	@Size(min = 3, max = 40)
	private String fromCurrency;

	@NotEmpty
	@Column(name = "to_currency")
	@Size(min = 3, max = 40)
	private String toCurrency;;

	@NotNull
	@Column(name = "deal_timestamp")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dealTimestamp;

	@NotNull
	@DecimalMin("0.0")
	@Column(name = "amount", precision = 10, scale = 3)
	private BigDecimal amount;

	public Deal() {

	}

	public Deal(String fromCurrency, String toCurrency, LocalDateTime dealTimestamp, BigDecimal amount) {
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.dealTimestamp = dealTimestamp;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public LocalDateTime getDealTimestamp() {
		return dealTimestamp;
	}

	public void setDealTimestamp(LocalDateTime dealTimestamp) {
		this.dealTimestamp = dealTimestamp;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Deal [id=" + id + "]";
	}

}
