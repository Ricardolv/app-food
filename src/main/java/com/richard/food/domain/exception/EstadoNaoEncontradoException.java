package com.richard.food.domain.exception;

import static com.richard.food.domain.util.ConstantesDomain.NÃO_EXISTE_CADASTRO_ESTADO;

public class EstadoNaoEncontradoException extends NegocioException {
    private static final long serialVersionUID = 1L;

    public EstadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEncontradoException(Long restauranteId) {
        this(String.format(NÃO_EXISTE_CADASTRO_ESTADO, restauranteId));
    }
}
