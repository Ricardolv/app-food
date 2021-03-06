package com.richard.food.resource;

import java.util.List;

import com.richard.food.resource.assembler.cozinha.CozinhaInputDisassembler;
import com.richard.food.resource.assembler.cozinha.CozinhaModelAssembler;
import com.richard.food.resource.model.CozinhaModel;
import com.richard.food.resource.model.input.CozinhaInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.richard.food.domain.model.Cozinha;
import com.richard.food.domain.service.CozinhaService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaResource {
	
	private final CozinhaModelAssembler cozinhaModelAssembler;
	private final CozinhaInputDisassembler cozinhaInputDisassembler;
	private final CozinhaService cozinhaService;

	public CozinhaResource(CozinhaModelAssembler cozinhaModelAssembler, CozinhaInputDisassembler cozinhaInputDisassembler, CozinhaService cozinhaService) {
		this.cozinhaModelAssembler = cozinhaModelAssembler;
		this.cozinhaInputDisassembler = cozinhaInputDisassembler;
		this.cozinhaService = cozinhaService;
	}

	@GetMapping
	public ResponseEntity<List<CozinhaModel>> listar() {
		List<Cozinha> todasCozinhas = cozinhaService.listar();
		List<CozinhaModel> collectionModel = cozinhaModelAssembler.toCollectionModel(todasCozinhas);
		return ResponseEntity.ok(collectionModel);
	}

	@GetMapping("/{cozinhaId}")
	public ResponseEntity<CozinhaModel> buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
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
