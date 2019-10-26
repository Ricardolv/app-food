package com.richard.food.api.resource;

import java.util.List;

import com.richard.food.api.assembler.CozinhaInputDisassembler;
import com.richard.food.api.model.input.CozinhaInput;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.richard.food.api.assembler.CozinhaModelAssembler;
import com.richard.food.api.model.CozinhaModel;
import com.richard.food.domain.model.Cozinha;
import com.richard.food.domain.service.CozinhaService;

import javax.validation.Valid;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaResource {
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;

	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	public ResponseEntity<List<CozinhaModel>> listar() {
		List<Cozinha> todasCozinhas = cozinhaService.listar();
		List<CozinhaModel> collectionModel = cozinhaModelAssembler.toCollectionModel(todasCozinhas);
		return ResponseEntity.ok(collectionModel);
	}

	@GetMapping("/{cozinhaId}")
	public ResponseEntity<CozinhaModel> buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cozinhaService.buscar(cozinhaId);
		CozinhaModel cozinhaModel = cozinhaModelAssembler.toModel(cozinha);
		return ResponseEntity.ok(cozinhaModel);
	}

	@PostMapping
	public ResponseEntity<CozinhaModel> adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		cozinha = cozinhaService.salvar(cozinha);
		CozinhaModel cozinhaModel = cozinhaModelAssembler.toModel(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaModel);
	}

	@PutMapping("/{cozinhaId}")
 	public ResponseEntity<CozinhaModel> atualizar(@PathVariable Long cozinhaId,
												  @RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);
		cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		cozinhaAtual = cozinhaService.salvar(cozinhaAtual);
		return ResponseEntity.ok(cozinhaModelAssembler.toModel(cozinhaAtual));
	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cozinhaService.excluir(cozinhaId);
	}
}
