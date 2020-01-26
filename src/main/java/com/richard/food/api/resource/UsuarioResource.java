package com.richard.food.api.resource;

import com.richard.food.api.assembler.usuario.UsuarioInputDisassembler;
import com.richard.food.api.assembler.usuario.UsuarioModelAssembler;
import com.richard.food.api.model.UsuarioModel;
import com.richard.food.api.model.input.SenhaInput;
import com.richard.food.api.model.input.UsuarioComSenhaInput;
import com.richard.food.api.model.input.UsuarioInput;
import com.richard.food.domain.model.Usuario;
import com.richard.food.domain.repository.UsuarioRepository;
import com.richard.food.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService cadastroUsuario;
    private final UsuarioModelAssembler usuarioModelAssembler;
    private final UsuarioInputDisassembler usuarioInputDisassembler;

    public UsuarioResource(UsuarioRepository usuarioRepository, UsuarioService cadastroUsuario, UsuarioModelAssembler usuarioModelAssembler, UsuarioInputDisassembler usuarioInputDisassembler) {
        this.usuarioRepository = usuarioRepository;
        this.cadastroUsuario = cadastroUsuario;
        this.usuarioModelAssembler = usuarioModelAssembler;
        this.usuarioInputDisassembler = usuarioInputDisassembler;
    }

    @GetMapping
    public List<UsuarioModel> listar() {
        List<Usuario> todasUsuarios = usuarioRepository.findAll();

        return usuarioModelAssembler.toCollectionModel(todasUsuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

        return usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        usuario = cadastroUsuario.salvar(usuario);

        return usuarioModelAssembler.toModel(usuario);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
                                  @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = cadastroUsuario.salvar(usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioAtual);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        cadastroUsuario.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
