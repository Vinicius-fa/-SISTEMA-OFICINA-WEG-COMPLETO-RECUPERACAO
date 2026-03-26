package com.weg.oficina.recuperacao.demo.repository;

import com.weg.oficina.recuperacao.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
