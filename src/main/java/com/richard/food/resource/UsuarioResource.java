package com.richard.food.resource;

import com.richard.food.domain.model.Usuario;
import com.richard.food.domain.repository.UsuarioRepository;
import com.richard.food.domain.service.UsuarioService;
import com.richard.food.resource.assembler.grupo.GrupoModelAssembler;
import com.richard.food.resource.assembler.usuario.UsuarioInputDisassembler;
import com.richard.food.resource.assembler.usuario.UsuarioModelAssembler;
import com.richard.food.resource.model.GrupoModel;
import com.richard.food.resource.model.UsuarioModel;
import com.richard.food.resource.model.input.SenhaInput;
import com.richard.food.resource.model.input.UsuarioComSenhaInput;
import com.richard.food.resource.model.input.UsuarioInput;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler usuarioModelAssembler;
    private final UsuarioInputDisassembler usuarioInputDisassembler;
    private final GrupoModelAssembler grupoModelAssembler;

    public UsuarioResource(UsuarioRepository usuarioRepository, UsuarioService usuarioService, UsuarioModelAssembler usuarioModelAssembler, UsuarioInputDisassembler usuarioInputDisassembler, GrupoModelAssembler grupoModelAssembler) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
        this.usuarioModelAssembler = usuarioModelAssembler;
        this.usuarioInputDisassembler = usuarioInputDisassembler;
        this.grupoModelAssembler = grupoModelAssembler;
    }

    @GetMapping
    public List<UsuarioModel> listar() {
        List<Usuario> todasUsuarios = usuarioRepository.findAll();

        return usuarioModelAssembler.toCollectionModel(todasUsuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        return usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        usuario = usuarioService.salvar(usuario);

        return usuarioModelAssembler.toModel(usuario);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
                                  @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = usuarioService.salvar(usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioAtual);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }

    @GetMapping("/{usuarioId}/grupos")
    public List<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
    }

}
