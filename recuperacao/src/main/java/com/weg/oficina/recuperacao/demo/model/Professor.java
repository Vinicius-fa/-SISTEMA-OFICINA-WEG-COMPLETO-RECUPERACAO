package com.weg.oficina.recuperacao.demo.model;

import com.weg.oficina.recuperacao.demo.enums.TipoUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "professores")
@Getter
@Setter
@NoArgsConstructor
public class Professor extends Usuario{

    public Professor(String nome) {
        super(null, nome, TipoUsuario.PROFESSOR);
    }
}
