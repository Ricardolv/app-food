package com.richard.food.resource.assembler.estado;

import com.richard.food.domain.model.Estado;
import com.richard.food.resource.model.input.EstadoInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDisassembler {

    private final ModelMapper modelMapper;

    public EstadoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Estado toDomainObject(EstadoInput estadoInput) {
        return modelMapper.map(estadoInput, Estado.class);
    }

    public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
        modelMapper.map(estadoInput, estado);
    }
}
