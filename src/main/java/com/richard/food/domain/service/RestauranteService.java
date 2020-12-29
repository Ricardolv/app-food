package com.richard.food.domain.service;

import com.richard.food.domain.exception.NegocioException;
import com.richard.food.domain.exception.RestauranteNaoEncontradoException;
import com.richard.food.domain.model.*;
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
    private final FormaPagamentoService formaPagamentoService;
    private final UsuarioService usuarioService;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaService cozinhaService, CidadeService cidadeService, FormaPagamentoService formaPagamentoService, UsuarioService usuarioService) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
        this.cidadeService = cidadeService;
        this.formaPagamentoService = formaPagamentoService;
        this.usuarioService = usuarioService;
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

    @Transactional
    public void ativar(List<Long> restautanteIDs ) {

        try {
            restautanteIDs.forEach(this::ativar);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @Transactional
    public void inativar(List<Long> restautanteIDs ) {

        try {
            restautanteIDs.forEach(this::inativar);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.abrir();
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.fechar();
    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        restaurante.removerResponsavel(usuario);
    }

    @Transactional
    public void associarResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        restaurante.adicionarResponsavel(usuario);
    }

}
