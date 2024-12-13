package edu.ifam.msf.msf.cidade;

import edu.ifam.msf.msf.pais.Pais;
import jakarta.persistence.*;

@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne(optional = false)
    private Pais pais;

    public Cidade() {
    }

    public Cidade(Long id, String nome, Pais pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
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

    public Pais getEstado() {
        return pais;
    }

    public void setEstado(Pais pais) {
        this.pais = pais;
    }

}
