package com.richard.food.domain.service;

import com.richard.food.domain.exception.RestauranteNaoEncontradoException;
import com.richard.food.domain.model.Cidade;
import com.richard.food.domain.model.Cozinha;
import com.richard.food.domain.model.Restaurante;
import com.richard.food.domain.repository.RestauranteRepository;
import com.richard.food.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import com.richard.food.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaService cozinhaService;
    private final CidadeService cidadeService;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaService cozinhaService, CidadeService cidadeService) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
        this.cidadeService = cidadeService;
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
        Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return nonNull(restaurante.getId()) ? restauranteRepository.save(restaurante) : restauranteRepository.saveAndFlush(restaurante);
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
