package com.richard.food.api.assembler.produto;

import com.richard.food.api.model.ProdutoModel;
import com.richard.food.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelAssembler {

    private final ModelMapper modelMapper;

    public ProdutoModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProdutoModel toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoModel.class);
    }

    public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(produto -> toModel(produto))
                .collect(Collectors.toList());
    }
}
