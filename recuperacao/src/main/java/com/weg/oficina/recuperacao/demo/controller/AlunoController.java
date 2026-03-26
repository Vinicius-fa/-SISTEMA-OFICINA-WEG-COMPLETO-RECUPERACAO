package com.weg.oficina.recuperacao.demo.controller;

import com.weg.oficina.recuperacao.demo.model.Aluno;
import com.weg.oficina.recuperacao.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        Aluno createdAluno = alunoService.criarAluno(aluno);
        return new ResponseEntity<>(createdAluno, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends Object> acharAlunoporId(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.encontrarPorId(id);
        return aluno != null ?
                new ResponseEntity<>(aluno, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoService.listarAlunos();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno updatedAluno = alunoService.atualizarAluno(id, aluno);
        return updatedAluno != null ?
                new ResponseEntity<>(updatedAluno, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        boolean isDeleted = alunoService.deletarAluno(id);
        return isDeleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
