package com.br.avaliacao.Avaliacao.exceptions;

public class MethodArgumentNotValid {

	private String field;
	private String message;

	public MethodArgumentNotValid(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
