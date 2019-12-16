package com.richard.food.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.richard.food.domain.excepiton.CozinhaNaoEncontradaException;
import com.richard.food.domain.excepiton.EntidadeEmUsoException;
import com.richard.food.domain.model.Cozinha;
import com.richard.food.domain.service.CozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CozinhaServiceTest {

    @Autowired
    private CozinhaService cozinhaService;

    @Test
    public void testarCadastroCozinhaComSucesso() {
        // Dado
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // Quando
        novaCozinha = cozinhaService.salvar(novaCozinha);

        // Entao
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testarCadastroCozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        novaCozinha = cozinhaService.salvar(novaCozinha);
    }

    @Test(expected = EntidadeEmUsoException.class)
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {
        cozinhaService.excluir(1L);
    }

    @Test(expected = CozinhaNaoEncontradaException.class)
    public void deveFalhar_QuandoExcluirCozinhaInexistente() {
        cozinhaService.excluir(100L);
    }
}
