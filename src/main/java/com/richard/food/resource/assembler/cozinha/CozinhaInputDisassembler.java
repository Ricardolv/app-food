package com.richard.food.resource.assembler.cozinha;

import com.richard.food.resource.model.input.CozinhaInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.richard.food.domain.model.Cozinha;

@Component
public class CozinhaInputDisassembler {
	
	private final ModelMapper modelMapper;

	public CozinhaInputDisassembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Cozinha toDomainObject(CozinhaInput cozinhaInput) {
		return modelMapper.map(cozinhaInput, Cozinha.class);
	}
	
	public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
		modelMapper.map(cozinhaInput, cozinha);
	}
}
