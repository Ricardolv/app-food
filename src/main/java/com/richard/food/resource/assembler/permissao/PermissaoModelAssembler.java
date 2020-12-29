package com.richard.food.resource.assembler.permissao;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.richard.food.domain.model.Permissao;
import com.richard.food.resource.model.PermissaoModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class PermissaoModelAssembler {

    private final ModelMapper modelMapper;

    public PermissaoModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PermissaoModel toModel(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoModel.class);
    }

    public List<PermissaoModel> toCollectionModel(Collection<Permissao> permissoes) {
        return permissoes.stream()
                .map(permissao -> toModel(permissao))
                .collect(Collectors.toList());
    }
}
