package com.weg.oficina.recuperacao.demo.service;

import com.weg.oficina.recuperacao.demo.model.OrdemServico;
import com.weg.oficina.recuperacao.demo.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServico criarOrdemServico(OrdemServico ordemServico) {
        return ordemServicoRepository.save(ordemServico);
    }

    public Optional<OrdemServico> buscarOrdemServicoPorId(Long id) {
        return ordemServicoRepository.findById(id);
    }

    public List<OrdemServico> listarOrdensServicos() {
        return ordemServicoRepository.findAll();
    }

    public OrdemServico atualizarOrdemServico(Long id, OrdemServico ordemServicoDetails) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ordem Servico com o ID " + id + "não encontrado"));
        ordemServico.setStatus(ordemServico.getStatus());
        return ordemServicoRepository.save(ordemServico);
    }

    public boolean deletarOrdemServico(Long id) {
        ordemServicoRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Ordem Servico com o ID " + id + " não encontrado"));
        ordemServicoRepository.deleteById(id);
        return false;
    }
}