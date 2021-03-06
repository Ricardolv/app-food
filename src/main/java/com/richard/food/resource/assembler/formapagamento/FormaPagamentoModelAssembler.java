package com.richard.food.resource.assembler.formapagamento;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.richard.food.domain.model.FormaPagamento;
import com.richard.food.resource.model.FormaPagamentoModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoModelAssembler {

    private final ModelMapper modelMapper;

    public FormaPagamentoModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
    }

    public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> formasPagamentos) {
        return formasPagamentos.stream()
                .map(formaPagamento -> toModel(formaPagamento))
                .collect(Collectors.toList());
    }
}
