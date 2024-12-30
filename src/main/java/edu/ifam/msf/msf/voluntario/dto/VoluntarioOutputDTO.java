package edu.ifam.msf.msf.voluntario.dto;

import edu.ifam.msf.msf.situacaoVoluntario.enums.SituacaoVoluntarioEnum;
import edu.ifam.msf.msf.voluntario.Voluntario;
import io.swagger.v3.oas.annotations.media.Schema;

public class VoluntarioOutputDTO {

    @Schema(description = "ID do voluntário", example = "101")
    private Long id;

    @Schema(description = "Passaporte do voluntário", example = "AB1234567")
    private String passaporte;

    @Schema(description = "Nome do voluntário", example = "João Silva")
    private String nome;

    @Schema(description = "Idade do voluntário", example = "25")
    private String idade;

    @Schema(description = "Telefone de contato", example = "+55 (92) 91234-5678")
    private String telefone;

    @Schema(description = "E-mail do voluntário", example = "joao.silva@example.com")
    private String email;

    @Schema(description = "Tipo sanguíneo do voluntário", example = "O+")
    private String tipoSanguineo;

    @Schema(description = "Nome da cidade de residência", example = "Manaus")
    private String cidade;

    @Schema(description = "Situação atual do voluntário", example = "BOM")
    private SituacaoVoluntarioEnum situacaoVoluntario;

    // Constructor

    public VoluntarioOutputDTO(Voluntario voluntario) {
        this.id = voluntario.getId();
        this.passaporte = voluntario.getPassaporte();
        this.nome = voluntario.getNome();
        this.idade = voluntario.getIdade();
        this.telefone = voluntario.getTelefone();
        this.email = voluntario.getEmail();
        this.tipoSanguineo = voluntario.getTipoSanguineo();
        this.cidade = voluntario.getCidade() != null ? voluntario.getCidade().getNome() : null;
        this.situacaoVoluntario = voluntario.getSituacaoVoluntario() != null ? voluntario.getSituacaoVoluntario().getSituacao() : null;
    }

    // Getters e setters

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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public SituacaoVoluntarioEnum getSituacaoVoluntario() {
        return situacaoVoluntario;
    }

    public void setSituacaoVoluntario(SituacaoVoluntarioEnum situacaoVoluntario) {
        this.situacaoVoluntario = situacaoVoluntario;
    }
}
