package com.weg.oficina.recuperacao.demo.controller;

import com.weg.oficina.recuperacao.demo.model.Professor;
import com.weg.oficina.recuperacao.demo.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarProfessors() {
        List<Professor> professors = professorService.professores();
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Professor>> acharProfessorPorId(@PathVariable Long id) {
        Optional<Professor> professor = professorService.buscarPorId(id);
        if (professor != null) {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Professor> criarProfessor(@RequestBody Professor professor) {
        Professor createdProfessor = professorService.criarProfessor(professor);
        return new ResponseEntity<>(createdProfessor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        professor.setId(id);
        Professor updatedProfessor = professorService.atualizarProfessor(professor);
        if (updatedProfessor != null) {
            return new ResponseEntity<>(updatedProfessor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id) {
        boolean isDeleted = professorService.deletarProfessor(id);
        return isDeleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}