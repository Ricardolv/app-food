package com.richard.food.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richard.food.domain.model.Restaurante;
import org.springframework.data.repository.query.Param;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
	Optional<Restaurante> findFirstByNome(String nome);

	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

}
