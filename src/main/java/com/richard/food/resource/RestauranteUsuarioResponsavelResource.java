package com.richard.food.resource;

import com.richard.food.domain.model.Restaurante;
import com.richard.food.domain.service.RestauranteService;
import com.richard.food.resource.assembler.usuario.UsuarioModelAssembler;
import com.richard.food.resource.model.UsuarioModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    private final RestauranteService cadastroRestaurante;
    private final UsuarioModelAssembler usuarioModelAssembler;

    public RestauranteUsuarioResponsavelController(RestauranteService cadastroRestaurante, UsuarioModelAssembler usuarioModelAssembler) {
        this.cadastroRestaurante = cadastroRestaurante;
        this.usuarioModelAssembler = usuarioModelAssembler;
    }

    @GetMapping
    public List<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis());
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestaurante.desassociarResponsavel(restauranteId, usuarioId);
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestaurante.associarResponsavel(restauranteId, usuarioId);
    }

}
