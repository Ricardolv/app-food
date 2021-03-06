package com.richard.food.resource.assembler.produto;

import com.richard.food.domain.model.Produto;
import com.richard.food.resource.model.input.ProdutoInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {

    private final ModelMapper modelMapper;

    public ProdutoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Produto toDomainObject(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
        modelMapper.map(produtoInput, produto);
    }
}
