package edu.ifam.msf.msf.situacaoVoluntario;

import edu.ifam.msf.msf.situacaoVoluntario.enums.SituacaoVoluntarioEnum;
import edu.ifam.msf.msf.voluntario.Voluntario;
import jakarta.persistence.*;

@Entity
public class SituacaoVoluntario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoVoluntarioEnum situacao;

    // Constructor

    public SituacaoVoluntario() {
    }

    public SituacaoVoluntario(Long id, SituacaoVoluntarioEnum situacao) {
        this.id = id;
        this.situacao = situacao;
    }

    // Getters e setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SituacaoVoluntarioEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoVoluntarioEnum situacao) {
        this.situacao = situacao;
    }
}
