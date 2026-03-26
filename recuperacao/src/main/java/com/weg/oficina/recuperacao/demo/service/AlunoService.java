package com.weg.oficina.recuperacao.demo.service;

import com.weg.oficina.recuperacao.demo.model.Aluno;
import com.weg.oficina.recuperacao.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarAlunos(){
        return alunoRepository.findAll();
    }

    public Optional<Aluno> encontrarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public Aluno criarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizarAluno(Long id, Aluno info) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno com o ID " + id + " não encontrado"));
        aluno.setNome(info.getNome());
        return alunoRepository.save(aluno);
    }

    public void deletarAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno com o ID " + id + " não encontrado"));
        alunoRepository.delete(aluno);
    }
}
