package com.br.avaliacao.Avaliacao.exceptions;

import javax.persistence.EntityNotFoundException;

public class EntityNotFound extends EntityNotFoundException{
	
	private static final long serialVersionUID = 1L;

	public EntityNotFound(String msg) {
		super(msg);
	}
	

}
