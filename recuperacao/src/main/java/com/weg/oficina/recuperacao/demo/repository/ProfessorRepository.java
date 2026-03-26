package com.weg.oficina.recuperacao.demo.repository;

import com.weg.oficina.recuperacao.demo.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
