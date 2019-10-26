package com.richard.food.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.richard.food.api.model.CozinhaModel;
import com.richard.food.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
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
