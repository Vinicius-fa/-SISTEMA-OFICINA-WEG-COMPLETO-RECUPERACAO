package com.weg.oficina.recuperacao.demo.service;

import com.weg.oficina.recuperacao.demo.model.Professor;
import com.weg.oficina.recuperacao.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> professores() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }

    public Professor criarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor atualizarProfessor( Professor professor) {
        return professorRepository.save(professor);
    }

    public boolean deletarProfessor(Long id) {
        professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor com o ID " + id + " não encontrado"));
        professorRepository.deleteById(id);
        return false;
    }
}
