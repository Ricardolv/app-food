package com.richard.food.resource;

import com.richard.food.domain.model.Pedido;
import com.richard.food.domain.service.PedidoService;
import com.richard.food.resource.assembler.pedido.PedidoModelAssembler;
import com.richard.food.resource.model.PedidoModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoModelAssembler pedidoModelAssembler;

    public PedidoController(PedidoService pedidoService, PedidoModelAssembler pedidoModelAssembler) {
        this.pedidoService = pedidoService;
        this.pedidoModelAssembler = pedidoModelAssembler;
    }

    @GetMapping
    public List<PedidoModel> listar() {
        List<Pedido> todosPedidos = pedidoService.findAll();

        return pedidoModelAssembler.toCollectionModel(todosPedidos);
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);

        return pedidoModelAssembler.toModel(pedido);
    }
}
