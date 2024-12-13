package edu.ifam.msf.msf.voluntario.dto;

import edu.ifam.msf.msf.cidade.Cidade;
import edu.ifam.msf.msf.situacaoVoluntario.SituacaoVoluntario;
import edu.ifam.msf.msf.voluntario.Voluntario;
import edu.ifam.msf.msf.cidade.CidadeRepository;
import edu.ifam.msf.msf.situacaoVoluntario.SituacaoVoluntarioRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class VoluntarioInputDTO {

    @NotBlank(message = "O passaporte é obrigatório")
    private String passaporte;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A idade é obrigatória")
    @Pattern(regexp = "\\d+", message = "A idade deve ser um número válido.")
    private String idade;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    private String email;

    @NotBlank(message = "O tipo sanguíneo é obrigatório")
    private String tipoSanguineo;

    @NotNull(message = "O ID da cidade é obrigatório")
    private Long cidade;

    @NotNull(message = "O ID da situação do voluntário é obrigatório")
    private Long situacaoVoluntario;

    // Constructor

    public VoluntarioInputDTO() {
    }

    // Getters e setters

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

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public Long getCidade() { return cidade; }

    public void setCidade(Long cidade) { this.cidade = cidade; }

    public Long getSituacaoVoluntario() { return situacaoVoluntario; }

    public void setSituacaoVoluntario(Long situacaoVoluntario) { this.situacaoVoluntario = situacaoVoluntario; }

    public Voluntario build(CidadeRepository cidadeRepository, SituacaoVoluntarioRepository situacaoVoluntarioRepository) {

        // Busca a Cidade pelo ID
        Cidade cidade = cidadeRepository.findById(this.cidade)
                .orElseThrow(() -> new RuntimeException("Cidade com ID " + this.cidade + " não encontrada."));

        // Busca a Situação pelo ID
        SituacaoVoluntario situacao = situacaoVoluntarioRepository.findById(this.situacaoVoluntario)
                .orElseThrow(() -> new RuntimeException("Situação com ID " + this.situacaoVoluntario + " não encontrada."));

        // Retorna o Voluntário montado
        return new Voluntario(
                null,
                this.passaporte,
                this.nome,
                this.idade,
                this.telefone,
                this.email,
                this.tipoSanguineo,
                cidade,
                situacao
        );

    }
}
