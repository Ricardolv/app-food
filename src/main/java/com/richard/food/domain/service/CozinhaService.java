package com.richard.food.domain.service;

import java.util.List;

import com.richard.food.domain.exception.CozinhaNaoEncontradaException;
import com.richard.food.domain.exception.EntidadeEmUsoException;
import com.richard.food.domain.util.ConstantesDomain;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.richard.food.domain.model.Cozinha;
import com.richard.food.domain.repository.CozinhaRepository;

import static java.util.Objects.nonNull;

@Service
public class CozinhaService {
	
	private final CozinhaRepository cozinhaRepository;

	public CozinhaService(CozinhaRepository cozinhaRepository) {
		this.cozinhaRepository = cozinhaRepository;
	}

	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	public Cozinha salvar(Cozinha cozinha) {
		return  nonNull(cozinha.getId()) ? cozinhaRepository.save(cozinha) : cozinhaRepository.saveAndFlush(cozinha);
	}

	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);

		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(ConstantesDomain.MSG_COZINHA_EM_USO, cozinhaId));
		}
	}

	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}
}
