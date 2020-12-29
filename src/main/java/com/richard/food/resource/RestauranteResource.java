package com.richard.food.resource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.food.application.validation.exceptions.ValidacaoException;
import com.richard.food.domain.exception.CidadeNaoEncontradaException;
import com.richard.food.domain.exception.CozinhaNaoEncontradaException;
import com.richard.food.domain.exception.NegocioException;
import com.richard.food.domain.model.Restaurante;
import com.richard.food.domain.service.RestauranteService;
import com.richard.food.resource.assembler.restaurante.RestauranteInputDisassembler;
import com.richard.food.resource.assembler.restaurante.RestauranteModelAssembler;
import com.richard.food.resource.model.RestauranteModel;
import com.richard.food.resource.model.input.RestauranteInput;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteResource {

    private final RestauranteModelAssembler restauranteModelAssembler;
    private final RestauranteInputDisassembler restauranteInputDisassembler;
    private final RestauranteService restauranteService;
    private final SmartValidator validator;

    public RestauranteResource(RestauranteModelAssembler restauranteModelAssembler, RestauranteInputDisassembler restauranteInputDisassembler, RestauranteService restauranteService, SmartValidator validator) {
        this.restauranteModelAssembler = restauranteModelAssembler;
        this.restauranteInputDisassembler = restauranteInputDisassembler;
        this.restauranteService = restauranteService;
        this.validator = validator;
    }

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
    public ResponseEntity<RestauranteModel> adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteModelAssembler.toModel(restauranteService.salvar(restaurante)));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<RestauranteModel> atualizar(@PathVariable Long restauranteId,
                                                      @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);
            restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
            return ResponseEntity.ok(restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual)));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos,
                                              HttpServletRequest request) {
        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

        if (isNull(restauranteAtual)) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual, request);
        validate(restauranteAtual, "restaurante");

        return ResponseEntity.ok(atualizar(restauranteId, restauranteModelAssembler.toModelInput(restauranteAtual)));
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        restauranteService.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        restauranteService.inativar(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        restauranteService.ativar(restauranteIds);
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
        restauranteService.inativar(restauranteIds);
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        restauranteService.abrir(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {
        restauranteService.fechar(restauranteId);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, new ServletServerHttpRequest(request));
        }

    }

    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
        validator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidacaoException(bindingResult);
        }
    }

}
