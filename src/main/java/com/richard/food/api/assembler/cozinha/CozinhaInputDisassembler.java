package com.richard.food.api.assembler.cozinha;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.richard.food.api.model.input.CozinhaInput;
import com.richard.food.domain.model.Cozinha;

@Component
public class CozinhaInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Cozinha toDomainObject(CozinhaInput cozinhaInput) {
		return modelMapper.map(cozinhaInput, Cozinha.class);
	}
	
	public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
		modelMapper.map(cozinhaInput, cozinha);
	}
}