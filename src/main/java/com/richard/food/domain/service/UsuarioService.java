package com.richard.food.domain.service;

import com.richard.food.domain.exception.NegocioException;
import com.richard.food.domain.exception.UsuarioNaoEncontradoException;
import com.richard.food.domain.model.Usuario;
import com.richard.food.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.nonNull;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario) {
        return nonNull(usuario) ? usuarioRepository.save(usuario) : usuarioRepository.saveAndFlush(usuario);
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);

        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }
}
