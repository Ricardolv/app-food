package com.richard.food.api.resource;

import com.richard.food.api.assembler.restaurante.RestauranteInputDisassembler;
import com.richard.food.api.assembler.restaurante.RestauranteModelAssembler;
import com.richard.food.api.model.RestauranteModel;
import com.richard.food.api.model.input.RestauranteInput;
import com.richard.food.domain.excepiton.CozinhaNaoEncontradaException;
import com.richard.food.domain.excepiton.NegocioException;
import com.richard.food.domain.model.Restaurante;
import com.richard.food.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteResource {

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteModelAssembler.toCollectionModel(restauranteService.listar());
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<RestauranteModel> buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        return ResponseEntity.ok(restauranteModelAssembler.toModel(restaurante));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

            return restauranteModelAssembler.toModel(restauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
                                      @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

            restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

            return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PatchMapping(("/{restauranteId}"))
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = restauranteService.buscar(restauranteId);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual);

        return ResponseEntity.ok(atualizar(restauranteId, restauranteModelAssembler.toModelInput(restauranteAtual)));
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            System.out.println(nomePropriedade + " = " + valorPropriedade);
        });
    }

}
