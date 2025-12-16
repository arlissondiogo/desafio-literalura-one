package com.example.literalura.model;

import jakarta.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getAnoNascimento() { return anoNascimento; }
    public void setAnoNascimento(Integer anoNascimento) { this.anoNascimento = anoNascimento; }

    public Integer getAnoFalecimento() { return anoFalecimento; }
    public void setAnoFalecimento(Integer anoFalecimento) { this.anoFalecimento = anoFalecimento; }

    @Override
    public String toString() {
        return """
               -------------------------
               Autor: %s
               Nascimento: %s
               Falecimento: %s
               -------------------------
               """.formatted(
                nome,
                anoNascimento != null ? anoNascimento : "Desconhecido",
                anoFalecimento != null ? anoFalecimento : "Vivo"
        );
    }
}
