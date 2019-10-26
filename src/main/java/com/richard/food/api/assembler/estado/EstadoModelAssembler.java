package com.richard.food.api.assembler.estado;

import com.richard.food.api.model.EstadoModel;
import com.richard.food.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoModel toModel(Estado estado) {
        return modelMapper.map(estado, EstadoModel.class);
    }

    public List<EstadoModel> toCollectionModel(List<Estado> estados) {

        List<EstadoModel> list = estados.stream()
                .map(estado -> toModel(estado))
                .collect(Collectors.toList());

        return list;
    }
}
