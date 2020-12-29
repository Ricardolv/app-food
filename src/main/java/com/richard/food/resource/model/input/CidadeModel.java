package com.richard.food.resource.model.input;

import com.richard.food.resource.model.EstadoModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeModel {

    private Long id;
    private String nome;
    private EstadoModel estado;
}
