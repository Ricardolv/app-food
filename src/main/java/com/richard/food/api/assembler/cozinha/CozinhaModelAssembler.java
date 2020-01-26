package com.richard.food.api.assembler.cozinha;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.richard.food.api.model.CozinhaModel;
import com.richard.food.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler {
	
	private final ModelMapper modelMapper;

	public CozinhaModelAssembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public CozinhaModel toModel(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaModel.class);
	}
	
	public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
		
		List<CozinhaModel> list = cozinhas.stream()
					.map(cozinha -> toModel(cozinha))
					.collect(Collectors.toList());
		
		return list;
	}

}
