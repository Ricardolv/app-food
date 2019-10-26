package com.richard.food.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.richard.food.api.assembler.CozinhaModelAssembler;
import com.richard.food.api.model.CozinhaModel;
import com.richard.food.domain.model.Cozinha;
import com.richard.food.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaResource {
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	public ResponseEntity<List<CozinhaModel>> listar() {
		List<Cozinha> todasCozinhas = cozinhaService.listar();
		List<CozinhaModel> collectionModel = cozinhaModelAssembler.toCollectionModel(todasCozinhas);
		return ResponseEntity.ok(collectionModel);
	}

}
