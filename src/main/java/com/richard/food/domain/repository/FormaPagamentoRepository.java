package com.richard.food.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richard.food.domain.model.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}
