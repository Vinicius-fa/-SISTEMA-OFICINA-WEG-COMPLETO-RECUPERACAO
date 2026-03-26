package com.weg.oficina.recuperacao.demo.repository;

import com.weg.oficina.recuperacao.demo.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
