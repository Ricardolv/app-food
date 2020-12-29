package com.richard.food.resource;

import com.richard.food.domain.model.FormaPagamento;
import com.richard.food.domain.repository.FormaPagamentoRepository;
import com.richard.food.domain.service.FormaPagamentoService;
import com.richard.food.resource.assembler.formapagamento.FormaPagamentoInputDisassembler;
import com.richard.food.resource.assembler.formapagamento.FormaPagamentoModelAssembler;
import com.richard.food.resource.model.FormaPagamentoModel;
import com.richard.food.resource.model.input.FormaPagamentoInput;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoResource {

    private final FormaPagamentoRepository formaPagamentoRepository;
    private final FormaPagamentoService formaPagamentoService;
    private final FormaPagamentoModelAssembler formaPagamentoModelAssembler;
    private final FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    public FormaPagamentoResource(FormaPagamentoRepository formaPagamentoRepository, FormaPagamentoService formaPagamentoService, FormaPagamentoModelAssembler formaPagamentoModelAssembler, FormaPagamentoInputDisassembler formaPagamentoInputDisassembler) {
        this.formaPagamentoRepository = formaPagamentoRepository;
        this.formaPagamentoService = formaPagamentoService;
        this.formaPagamentoModelAssembler = formaPagamentoModelAssembler;
        this.formaPagamentoInputDisassembler = formaPagamentoInputDisassembler;
    }

    @GetMapping
    public List<FormaPagamentoModel> listar() {
        List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();

        return formaPagamentoModelAssembler.toCollectionModel(todasFormasPagamentos);
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        return formaPagamentoModelAssembler.toModel(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = formaPagamentoService.salvar(formaPagamento);

        return formaPagamentoModelAssembler.toModel(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
                                         @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {

        FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);

        return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.excluir(formaPagamentoId);
    }
}
