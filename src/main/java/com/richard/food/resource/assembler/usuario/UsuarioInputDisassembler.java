package com.richard.food.resource.assembler.usuario;

import com.richard.food.domain.model.Usuario;
import com.richard.food.resource.model.input.UsuarioInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

    private final ModelMapper modelMapper;

    public UsuarioInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Usuario toDomainObject(UsuarioInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario) {
        modelMapper.map(usuarioInput, usuario);
    }
}
