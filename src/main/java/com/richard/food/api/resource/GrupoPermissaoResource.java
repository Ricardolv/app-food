package com.richard.food.api.resource;

import com.richard.food.api.assembler.permissao.PermissaoModelAssembler;
import com.richard.food.api.model.PermissaoModel;
import com.richard.food.domain.model.Grupo;
import com.richard.food.domain.service.GrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes")
public class GrupoPermissaoResource {

    private final GrupoService grupoService;
    private final PermissaoModelAssembler permissaoModelAssembler;

    public GrupoPermissaoResource(GrupoService grupoService, PermissaoModelAssembler permissaoModelAssembler) {
        this.grupoService = grupoService;
        this.permissaoModelAssembler = permissaoModelAssembler;
    }

    @GetMapping
    public List<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);

        return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId, permissaoId);
    }

}
