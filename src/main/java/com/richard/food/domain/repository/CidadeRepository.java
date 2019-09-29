package com.richard.food.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richard.food.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
