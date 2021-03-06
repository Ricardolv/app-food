package com.richard.food.resource;

import com.richard.food.domain.exception.EstadoNaoEncontradoException;
import com.richard.food.domain.exception.NegocioException;
import com.richard.food.domain.model.Cidade;
import com.richard.food.domain.service.CidadeService;
import com.richard.food.resource.assembler.cidade.CidadeInputDisassembler;
import com.richard.food.resource.assembler.cidade.CidadeModelAssembler;
import com.richard.food.resource.model.CidadeModel;
import com.richard.food.resource.model.input.CidadeInput;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

    private final CidadeModelAssembler cidadeModelAssembler;
    private final CidadeInputDisassembler cidadeInputDisassembler;
    private final CidadeService cidadeService;

    public CidadeResource(CidadeModelAssembler cidadeModelAssembler, CidadeInputDisassembler cidadeInputDisassembler, CidadeService cidadeService) {
        this.cidadeModelAssembler = cidadeModelAssembler;
        this.cidadeInputDisassembler = cidadeInputDisassembler;
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public List<CidadeModel> listar() {
        List<Cidade> todasCidades = cidadeService.listar();
        return cidadeModelAssembler.toCollectionModel(todasCidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cidadeService.salvar(cidade);

            return cidadeModelAssembler.toModel(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Long cidadeId,
                                 @RequestBody @Valid CidadeInput cidadeInput) {
        try {

            Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
            cidadeAtual = cidadeService.salvar(cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cidadeService.excluir(cidadeId);
    }

}
