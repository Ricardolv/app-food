package com.richard.food.api.resource;

import com.richard.food.api.assembler.grupo.GrupoInputDisassembler;
import com.richard.food.api.assembler.grupo.GrupoModelAssembler;
import com.richard.food.api.model.GrupoModel;
import com.richard.food.api.model.input.GrupoInput;
import com.richard.food.domain.model.Grupo;
import com.richard.food.domain.repository.GrupoRepository;
import com.richard.food.domain.service.GrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoResource {

    private final GrupoRepository grupoRepository;
    private final GrupoService grupoService;
    private final GrupoModelAssembler grupoModelAssembler;
    private final GrupoInputDisassembler grupoInputDisassembler;

    public GrupoResource(GrupoRepository grupoRepository, GrupoService grupoService, GrupoModelAssembler grupoModelAssembler, GrupoInputDisassembler grupoInputDisassembler) {
        this.grupoRepository = grupoRepository;
        this.grupoService = grupoService;
        this.grupoModelAssembler = grupoModelAssembler;
        this.grupoInputDisassembler = grupoInputDisassembler;
    }

    @GetMapping
    public List<GrupoModel> listar() {
        List<Grupo> todosGrupos = grupoRepository.findAll();

        return grupoModelAssembler.toCollectionModel(todosGrupos);
    }

    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);

        return grupoModelAssembler.toModel(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);

        grupo = grupoService.salvar(grupo);

        return grupoModelAssembler.toModel(grupo);
    }

    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId,
                                @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);

        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

        grupoAtual = grupoService.salvar(grupoAtual);

        return grupoModelAssembler.toModel(grupoAtual);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);
    }
}
