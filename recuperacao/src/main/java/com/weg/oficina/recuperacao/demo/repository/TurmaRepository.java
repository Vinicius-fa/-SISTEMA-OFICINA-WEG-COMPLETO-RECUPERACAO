package com.weg.oficina.recuperacao.demo.repository;

import com.weg.oficina.recuperacao.demo.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
