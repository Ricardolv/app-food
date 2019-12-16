package com.richard.food.domain.exception;

import static com.richard.food.domain.util.ConstantesDomain.NÃO_EXISTE_CADASTRO_CIDADE;

public class CidadeNaoEncontradaException extends NegocioException {
    private static final long serialVersionUID = 1L;

    public CidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long restauranteId) {
        this(String.format(NÃO_EXISTE_CADASTRO_CIDADE, restauranteId));
    }
}
