package com.richard.food.api.model.input;

import com.richard.food.api.model.EstadoModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeModel {

    private Long id;
    private String nome;
    private EstadoModel estado;
}
