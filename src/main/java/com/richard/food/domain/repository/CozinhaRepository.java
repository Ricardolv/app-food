package com.richard.food.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richard.food.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	
	Optional<Cozinha> findByNome(String nome);

}
