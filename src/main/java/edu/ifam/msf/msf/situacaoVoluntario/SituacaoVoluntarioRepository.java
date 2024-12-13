package edu.ifam.msf.msf.situacaoVoluntario;

import edu.ifam.msf.msf.cidade.Cidade;
import edu.ifam.msf.msf.situacaoVoluntario.enums.SituacaoVoluntarioEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SituacaoVoluntarioRepository extends JpaRepository<SituacaoVoluntario, Long> {
    Optional<SituacaoVoluntario> findBySituacao(String situacao);
}
