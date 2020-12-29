package com.richard.food.resource.assembler.grupo;

import com.richard.food.domain.model.Grupo;
import com.richard.food.resource.model.input.GrupoInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDisassembler {

    private final ModelMapper modelMapper;

    public GrupoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Grupo toDomainObject(GrupoInput grupoInput) {
        return modelMapper.map(grupoInput, Grupo.class);
    }

    public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo) {
        modelMapper.map(grupoInput, grupo);
    }
}
