package com.weg.oficina.recuperacao.demo.service;

import com.weg.oficina.recuperacao.demo.model.Professor;
import com.weg.oficina.recuperacao.demo.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final ProfessorRepository professorRepository;

    public Optional<Professor> autenticarProfessor(Long id) {
        return professorRepository.findById(id);
    }
}
