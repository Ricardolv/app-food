package com.richard.food.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.richard.food.core.validation.Multiplo;
import com.richard.food.core.validation.TaxaFrete;
import com.richard.food.core.validation.ValorZeroIncluiDescricao;
import lombok.Getter;
import lombok.Setter;

//@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Setter
@Getter
public class RestauranteInput {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    //@TaxaFrete
    //@Multiplo(numero = 5)
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaIdInput cozinha;
}
