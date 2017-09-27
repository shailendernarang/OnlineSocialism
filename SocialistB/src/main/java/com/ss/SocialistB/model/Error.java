package com.ss.SocialistB.model;

/*
 * Sending Error Messages
 */
public class Error {

	public Error(int code, String message) {
		
		this.code = code;
		this.message = message;
	}
	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
