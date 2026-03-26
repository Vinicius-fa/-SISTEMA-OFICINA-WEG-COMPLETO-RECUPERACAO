package com.weg.oficina.recuperacao.demo.service;

import com.weg.oficina.recuperacao.demo.model.Usuario;
import com.weg.oficina.recuperacao.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario encontrarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
