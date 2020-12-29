package com.richard.food.resource.assembler.formapagamento;


import com.richard.food.domain.model.FormaPagamento;
import com.richard.food.resource.model.input.FormaPagamentoInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputDisassembler {

    private final ModelMapper modelMapper;

    public FormaPagamentoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
        return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoInput, formaPagamento);
    }
}
