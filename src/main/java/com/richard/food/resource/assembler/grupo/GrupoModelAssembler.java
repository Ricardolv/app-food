package com.richard.food.resource.assembler.grupo;

import java.util.List;
import java.util.stream.Collectors;

import com.richard.food.domain.model.Grupo;
import com.richard.food.resource.model.GrupoModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GrupoModelAssembler {

    private final ModelMapper modelMapper;

    public GrupoModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GrupoModel toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModel.class);
    }

    public List<GrupoModel> toCollectionModel(List<Grupo> grupos) {
        return grupos.stream()
                .map(grupo -> toModel(grupo))
                .collect(Collectors.toList());
    }
}
