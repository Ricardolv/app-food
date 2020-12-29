package com.richard.food.domain.service;

import com.richard.food.domain.exception.PedidoNaoEncontradoException;
import com.richard.food.domain.model.Pedido;
import com.richard.food.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
}
