package edu.ifam.msf.msf.pais;

import jakarta.persistence.*;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    // Constructor

    public Pais() {
    }

    public Pais(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
