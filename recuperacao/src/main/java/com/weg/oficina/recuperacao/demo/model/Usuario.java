package com.weg.oficina.recuperacao.demo.model;

import com.weg.oficina.recuperacao.demo.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @Override
    public String toString() {
        return "[ID " + id + "] " + nome + " (" + tipo + ")";
    }
}
