package com.richard.food.resource.assembler.pedido;

import com.richard.food.domain.model.Pedido;
import com.richard.food.resource.model.PedidoModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoModelAssembler {

    private final ModelMapper modelMapper;

    public PedidoModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PedidoModel toModel(Pedido pedido) {
        return modelMapper.map(pedido, PedidoModel.class);
    }

    public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toModel(pedido))
                .collect(Collectors.toList());
    }
}
