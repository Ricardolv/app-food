package com.richard.food.domain.exception;

import static com.richard.food.domain.util.ConstantesDomain.NÃO_EXISTE_CADASTRO_RESTAURANTE;

public class RestauranteNaoEncontradoException extends NegocioException {
    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long restauranteId) {
        this(String.format(NÃO_EXISTE_CADASTRO_RESTAURANTE, restauranteId));
    }
}

