package com.richard.food.domain.service;

import com.richard.food.domain.exception.RestauranteNaoEncontradoException;
import com.richard.food.domain.model.Cozinha;
import com.richard.food.domain.model.Restaurante;
import com.richard.food.domain.repository.RestauranteRepository;
import com.richard.food.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import com.richard.food.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaService cozinhaService;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaService cozinhaService) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    // Specification de forma normal
    public List<Restaurante> findAllFreGratisAndNomeSemelhante(String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
        return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
    }

    // Specification usando fabrica
    public List<Restaurante> findAllFreGratis(String nome) {
        return restauranteRepository.findComFreteGratis(nome);
    }

    public Optional<Restaurante> restaurantePrimeiro() {
        return restauranteRepository.buscarPrimeiro();
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.inativar();
    }
}
