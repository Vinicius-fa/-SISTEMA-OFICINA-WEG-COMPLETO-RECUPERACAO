package com.weg.oficina.recuperacao.demo.service;

import com.weg.oficina.recuperacao.demo.model.Aluno;
import com.weg.oficina.recuperacao.demo.model.Professor;
import com.weg.oficina.recuperacao.demo.model.Turma;
import com.weg.oficina.recuperacao.demo.repository.AlunoRepository;
import com.weg.oficina.recuperacao.demo.repository.ProfessorRepository;
import com.weg.oficina.recuperacao.demo.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurmaService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    public void inicializarSistema() {
        Professor p1 = professorRepository.save(new Professor("Mestre Ricardo (WEG)"));
        Professor p2 = professorRepository.save(new Professor("Mestre Alexandre (WEG)"));

        String[] nomes = {"Cibersistemas T1", "Cibersistemas T2", "Mecânica T3", "Mecânica T4"};
        Professor[] profs = {p1, p1, p2, p2};

        for (int t = 0; t < 4; t++) {
            Turma turma = new Turma(nomes[t], profs[t]);
            int quantidade = 10 + t;

            for (int i = 0; i < quantidade; i ++) {
                Aluno aluno = alunoRepository.save(new Aluno("Estudante_" + (t * 15 + i + 1)));
                turma.adicionarAluno(aluno);
            }
            turmaRepository.save(turma);
        }

        long totalAlunos = alunoRepository.count();
        System.out.println("Sistema WEG inicializando: 2 professores e " + totalAlunos + " Alunos");
    }

    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }
}
