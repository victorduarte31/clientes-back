package br.victor.clientes.rest.service;

import br.victor.clientes.model.entity.Usuario;
import br.victor.clientes.rest.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public void salvar(Usuario usuario) {
        boolean existeUsuario = usuarioRepository.existsByUserName(usuario.getUsername());
        if (existeUsuario) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existente na base!");
        }
        usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }
}
