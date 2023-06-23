package com.suliman.datawarehouse.wrapper;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {

	private int state;
	private String message;
	private LocalDateTime timestamp;

	public ErrorResponse() {

	}

	public ErrorResponse(int state, String message, LocalDateTime timestamp) {
		super();
		this.state = state;
		this.message = message;
		this.timestamp = timestamp;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
