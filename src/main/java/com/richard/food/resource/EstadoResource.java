package com.richard.food.resource;

import com.richard.food.domain.model.Estado;
import com.richard.food.domain.service.EstadoService;
import com.richard.food.resource.assembler.estado.EstadoInputDisassembler;
import com.richard.food.resource.assembler.estado.EstadoModelAssembler;
import com.richard.food.resource.model.EstadoModel;
import com.richard.food.resource.model.input.EstadoInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    private final EstadoModelAssembler estadoModelAssembler;
    private final EstadoInputDisassembler estadoInputDisassembler;
    private final EstadoService estadoService;

    public EstadoResource(EstadoModelAssembler estadoModelAssembler, EstadoInputDisassembler estadoInputDisassembler, EstadoService estadoService) {
        this.estadoModelAssembler = estadoModelAssembler;
        this.estadoInputDisassembler = estadoInputDisassembler;
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoModel>> listar() {
        List<Estado> estados = estadoService.listar();
        List<EstadoModel> collectionModel = estadoModelAssembler.toCollectionModel(estados);
        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<EstadoModel> buscar(@PathVariable Long estadoId) {
        Estado estado = estadoService.buscarOuFalhar(estadoId);
        return ResponseEntity.ok(estadoModelAssembler.toModel(estado));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
        estado = estadoService.salvar(estado);
        return estadoModelAssembler.toModel(estado);
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId,
                                 @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);
        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
        estadoAtual = estadoService.salvar(estadoAtual);
        return estadoModelAssembler.toModel(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        estadoService.excluir(estadoId);
    }

}
