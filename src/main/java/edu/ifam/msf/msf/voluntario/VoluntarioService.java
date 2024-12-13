package edu.ifam.msf.msf.voluntario;

import edu.ifam.msf.msf.voluntario.dto.VoluntarioInputDTO;
import edu.ifam.msf.msf.voluntario.dto.VoluntarioOutputDTO;
import edu.ifam.msf.msf.cidade.CidadeRepository;
import edu.ifam.msf.msf.situacaoVoluntario.SituacaoVoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private SituacaoVoluntarioRepository situacaoVoluntarioRepository;

    // Listar todos os voluntários
    public List<VoluntarioOutputDTO> list() {
        List<Voluntario> voluntarios = voluntarioRepository.findAll();
        List<VoluntarioOutputDTO> voluntarioDTOs = new ArrayList<>();

        for (Voluntario voluntario : voluntarios) {
            voluntarioDTOs.add(new VoluntarioOutputDTO(voluntario));
        }

        return voluntarioDTOs;
    }

    // Criar um novo voluntário
    public VoluntarioOutputDTO create(VoluntarioInputDTO voluntarioInputDTO) {
        try {
            // Converte o DTO para entidade, utilizando os dois repositórios
            Voluntario voluntario = voluntarioInputDTO.build(cidadeRepository, situacaoVoluntarioRepository);

            // Salva o voluntário no banco de dados
            Voluntario voluntarioSalvo = voluntarioRepository.save(voluntario);

            return new VoluntarioOutputDTO(voluntarioSalvo);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar voluntário: " + e.getMessage());
        }
    }

    // Buscar um voluntário pelo seu ID
    public VoluntarioOutputDTO getById(Long id) {
        Voluntario voluntario = voluntarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voluntário com ID " + id + " não encontrado."));

        return new VoluntarioOutputDTO(voluntario);
    }

    // Deletar um voluntário pelo ID
    public void delete(Long id) {
        Voluntario voluntario = voluntarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voluntário com ID " + id + " não encontrado."));

        voluntarioRepository.delete(voluntario);
    }

    // Atualizar os dados de um voluntário
    public VoluntarioOutputDTO update(VoluntarioInputDTO voluntarioInputDTO, Long id) {
        Voluntario voluntarioExistente = voluntarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voluntário com ID " + id + " não encontrado."));

        try {
            // Converte o DTO para entidade, utilizando os dois repositórios
            Voluntario voluntarioAtualizado = voluntarioInputDTO.build(cidadeRepository, situacaoVoluntarioRepository);

            // Atualiza apenas os campos necessários
            voluntarioExistente.setPassaporte(voluntarioAtualizado.getPassaporte());
            voluntarioExistente.setNome(voluntarioAtualizado.getNome());
            voluntarioExistente.setIdade(voluntarioAtualizado.getIdade());
            voluntarioExistente.setTelefone(voluntarioAtualizado.getTelefone());
            voluntarioExistente.setEmail(voluntarioAtualizado.getEmail());
            voluntarioExistente.setTipoSanguineo(voluntarioAtualizado.getTipoSanguineo());
            voluntarioExistente.setCidade(voluntarioAtualizado.getCidade());
            voluntarioExistente.setSituacaoVoluntario(voluntarioAtualizado.getSituacaoVoluntario());

            // Salva o voluntário atualizado no banco de dados
            Voluntario voluntarioSalvo = voluntarioRepository.save(voluntarioExistente);
            return new VoluntarioOutputDTO(voluntarioSalvo);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao atualizar voluntário: " + e.getMessage());
        }
    }
}
