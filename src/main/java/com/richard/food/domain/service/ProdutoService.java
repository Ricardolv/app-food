package com.richard.food.domain.service;

import com.richard.food.domain.exception.ProdutoNaoEncontradoException;
import com.richard.food.domain.model.Produto;
import com.richard.food.domain.model.Restaurante;
import com.richard.food.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
                                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }

    public List<Produto> findByRestaurante(Restaurante restaurante) {
        return produtoRepository.findByRestaurante(restaurante);
    }
}
