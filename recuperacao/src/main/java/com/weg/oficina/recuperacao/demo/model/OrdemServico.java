package com.weg.oficina.recuperacao.demo.model;

import com.weg.oficina.recuperacao.demo.enums.StatusOs;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordens_servico")
@Getter
@Setter
@NoArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String equipamento;
    private String defeitoRelato;
    private String materiasiUsados;
    private String laudoTecnico;

    @Enumerated(EnumType.STRING)
    private StatusOs status;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professorResponsavel;

    @ManyToMany
    @JoinTable(name = "os_alunos", joinColumns = @JoinColumn(name = "os_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> alunosEscalados = new ArrayList<>();

    public OrdemServico(String equipamento, String defeitoRelato, Professor professor) {
        this.equipamento = equipamento;
        this.defeitoRelato = defeitoRelato;
        this.professorResponsavel = professor;
        this.status = StatusOs.EXECUTANDO;
        this.alunosEscalados = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        alunosEscalados.add(aluno);
    }

    public boolean alunoEstaEscalado(Aluno aluno) {
        return alunosEscalados.stream()
                .anyMatch(a -> a.getId().equals(aluno.getId()));
    }

    public boolean podeSerAprovadaPor (Professor professor) {
        return professorResponsavel.getId().equals(professor.getId());
    }
}
