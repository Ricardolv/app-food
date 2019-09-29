package com.richard.food.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richard.food.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
