package edu.ifam.msf.msf.voluntario;

import edu.ifam.msf.msf.cidade.Cidade;
import edu.ifam.msf.msf.situacaoVoluntario.SituacaoVoluntario;
import jakarta.persistence.*;

@Entity
public class Voluntario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String passaporte;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String idade;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String tipoSanguineo;

    @ManyToOne(optional = false)
    private Cidade cidade;

    @ManyToOne(optional = false)
    private SituacaoVoluntario situacaoVoluntario;

    // Constructor

    public Voluntario() {
    }

    public Voluntario(Long id, String passaporte, String nome, String idade, String telefone, String email, String tipoSanguineo, Cidade cidade, SituacaoVoluntario situacaoVoluntario) {
        this.id = id;
        this.passaporte = passaporte;
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
        this.tipoSanguineo = tipoSanguineo;
        this.cidade = cidade;
        this.situacaoVoluntario = situacaoVoluntario;
    }

    // Getters e Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipo_sanguineo) {
        this.tipoSanguineo = tipo_sanguineo;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public SituacaoVoluntario getSituacaoVoluntario() {
        return situacaoVoluntario;
    }

    public void setSituacaoVoluntario(SituacaoVoluntario situacaoVoluntario) {
        this.situacaoVoluntario = situacaoVoluntario;
    }
}
