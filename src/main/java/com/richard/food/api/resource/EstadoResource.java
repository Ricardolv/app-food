package com.richard.food.api.resource;

import com.richard.food.api.assembler.EstadoModelAssembler;
import com.richard.food.api.model.EstadoModel;
import com.richard.food.domain.model.Estado;
import com.richard.food.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @GetMapping
    public ResponseEntity<List<EstadoModel>> listar() {
        List<Estado> estados = estadoService.listar();
        List<EstadoModel> collectionModel = estadoModelAssembler.toCollectionModel(estados);
        return ResponseEntity.ok(collectionModel);
    }
}
