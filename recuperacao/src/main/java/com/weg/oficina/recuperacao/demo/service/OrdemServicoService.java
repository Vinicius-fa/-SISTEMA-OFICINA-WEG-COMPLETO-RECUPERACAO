package com.weg.oficina.recuperacao.demo.service;

import com.weg.oficina.recuperacao.demo.enums.StatusOs;
import com.weg.oficina.recuperacao.demo.model.Aluno;
import com.weg.oficina.recuperacao.demo.model.OrdemServico;
import com.weg.oficina.recuperacao.demo.model.Professor;
import com.weg.oficina.recuperacao.demo.repository.AlunoRepository;
import com.weg.oficina.recuperacao.demo.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final AlunoRepository alunoRepository;

    public OrdemServico abrirOS(Professor professor, String equipamento, String defeito, Long idAluno1, Long idAluno2) {
        Aluno a1 = alunoRepository.findById(idAluno1)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID " + idAluno1));
        Aluno a2 = alunoRepository.findById(idAluno2)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID " + idAluno2));

        OrdemServico os = new OrdemServico(equipamento, defeito, professor);
            os.adicionarAluno(a1);
            os.adicionarAluno(a2);
            return ordemServicoRepository.save(os);
    }

    public void registrarExecucao(Long idOS, Long idAluno, String materiais, String laudo) {
        OrdemServico os = buscarOuErro (idOS);
        Aluno aluno = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID " + idAluno));

        if(!os.alunoEstaEscalado(aluno)) {
            throw new SecurityException("Aluno não esta escalado para esta OS.");
        }
        if (!os.getStatus().equals(StatusOs.EXECUTANDO)) {
            throw new IllegalStateException("OS não está em execução.");
        }

        os.setMateriasiUsados(materiais);
        os.setLaudoTecnico(laudo);
        os.setStatus(StatusOs.AGUARDANDO_APROVACAO);
        ordemServicoRepository.save(os);
    }

    public void aprovarOs(long idOS, Professor professor) {
        OrdemServico os = buscarOuErro(idOS);

        if(!os.podeSerAprovadaPor(professor)){
            throw new SecurityException("Apenas o professor responsavel pode encerrar esta OS");
        }
        if (!os.getStatus().equals(StatusOs.AGUARDANDO_APROVACAO)) {
            throw new IllegalStateException("OS não está aguardando aprovação.");
        }

        os.setStatus(StatusOs.CONCLUITDA);
        ordemServicoRepository.save(os);

        System.out.println("OS numero " + os.getId() + " encerrada!");
        System.out.println("Equipamentos : " + os.getEquipamento());
        System.out.println("Materiais : " + os.getMateriasiUsados());
        System.out.println("Laudo : " + os.getLaudoTecnico());
    }

    private OrdemServico buscarOuErro(Long id) {
        return ordemServicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OS numero " + id + " não encontrado"));
    }
}