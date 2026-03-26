package com.weg.oficina.recuperacao.demo.controller;

import com.weg.oficina.recuperacao.demo.model.Turma;
import com.weg.oficina.recuperacao.demo.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping("/inicializar")
    public ResponseEntity<String> inicializarSistema() {
        turmaService.inicializarSistema();
        return ResponseEntity.ok("Sistema inicializado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Turma>> listarTurmas() {
        List<Turma> turmas = turmaService.listarTurmas();
        return ResponseEntity.ok(turmas);
    }
}
