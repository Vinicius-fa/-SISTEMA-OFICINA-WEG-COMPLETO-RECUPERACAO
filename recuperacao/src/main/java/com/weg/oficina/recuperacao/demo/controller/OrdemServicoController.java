package com.weg.oficina.recuperacao.demo.controller;

import com.weg.oficina.recuperacao.demo.model.OrdemServico;
import com.weg.oficina.recuperacao.demo.service.OrdemServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<OrdemServico> criarOrdem(@RequestBody OrdemServico ordemServico) {
        OrdemServico created = ordemServicoService.criarOrdemServico(ordemServico);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> encontrarPorId(@PathVariable Long id) {
        return ordemServicoService.buscarOrdemServicoPorId(id)
                .map(ordem -> new ResponseEntity<>(ordem, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listarOrdens() {
        List<OrdemServico> ordens = ordemServicoService.listarOrdensServicos();
        return new ResponseEntity<>(ordens, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServico> atualizarOrdem(@PathVariable Long id, @RequestBody OrdemServico ordemServico) {
        OrdemServico updated = ordemServicoService.atualizarOrdemServico(id, ordemServico);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdem(@PathVariable Long id) {
        boolean isDeleted = ordemServicoService.deletarOrdemServico(id);
        return isDeleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
