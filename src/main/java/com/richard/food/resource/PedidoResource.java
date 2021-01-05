package com.richard.food.resource;

import com.richard.food.domain.model.Pedido;
import com.richard.food.domain.service.PedidoService;
import com.richard.food.resource.assembler.pedido.PedidoModelAssembler;
import com.richard.food.resource.assembler.pedido.PedidoResumoModelAssembler;
import com.richard.food.resource.model.PedidoModel;
import com.richard.food.resource.model.PedidoResumoModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    private final PedidoService pedidoService;
    private final PedidoModelAssembler pedidoModelAssembler;
    private final PedidoResumoModelAssembler pedidoResumoModelAssembler;

    public PedidoResource(PedidoService pedidoService, PedidoModelAssembler pedidoModelAssembler, PedidoResumoModelAssembler pedidoResumoModelAssembler) {
        this.pedidoService = pedidoService;
        this.pedidoModelAssembler = pedidoModelAssembler;
        this.pedidoResumoModelAssembler = pedidoResumoModelAssembler;
    }

    @GetMapping
    public List<PedidoResumoModel> listar() {
        List<Pedido> todosPedidos = pedidoService.findAll();

        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);

        return pedidoModelAssembler.toModel(pedido);
    }
}
