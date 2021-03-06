package com.richard.food.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richard.food.domain.model.Pedido;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
    List<Pedido> findAll();

}
