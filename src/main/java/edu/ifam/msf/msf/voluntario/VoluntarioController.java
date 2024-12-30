package edu.ifam.msf.msf.voluntario;

import edu.ifam.msf.msf.voluntario.dto.VoluntarioInputDTO;
import edu.ifam.msf.msf.voluntario.dto.VoluntarioOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/voluntario")
@Tag(name = "Voluntarios", description = "APIs para gerenciamento de voluntários")
public class VoluntarioController {

    @Autowired
    private VoluntarioService voluntarioService;

    @GetMapping
    @Operation(
            summary = "Listar todos os voluntários",
            description = "Retorna uma lista de todas as pessoas registradas como voluntários."
    )
    public ResponseEntity<List<VoluntarioOutputDTO>> list() {
        try {
            List<VoluntarioOutputDTO> voluntariosDTO = voluntarioService.list();
            return new ResponseEntity<>(voluntariosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Cria um novo voluntário.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Criar novo voluntário",
            description = "Registra um novo voluntário no sistema. O corpo da requisição deve conter os dados obrigatórios."
    )
    public ResponseEntity<VoluntarioOutputDTO> create(@Valid @RequestBody VoluntarioInputDTO voluntarioInputDTO) {
        try {
            VoluntarioOutputDTO voluntarioOutputDTO = voluntarioService.create(voluntarioInputDTO);
            return new ResponseEntity<>(voluntarioOutputDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Atualizar dados de um voluntário",
            description = "Atualiza os dados de um voluntário existente identificado pelo ID."
    )
    public ResponseEntity<VoluntarioOutputDTO> update(@Valid @RequestBody VoluntarioInputDTO voluntarioInputDTO, @PathVariable Long id) {
        try {
            VoluntarioOutputDTO voluntarioAtualizado = voluntarioService.update(voluntarioInputDTO, id);
            return new ResponseEntity<>(voluntarioAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar voluntário por ID",
            description = "Recupera os dados de um voluntário a partir do seu identificador único."
    )
    public ResponseEntity<VoluntarioOutputDTO> getById(@PathVariable Long id) {
        try {
            VoluntarioOutputDTO voluntario = voluntarioService.getById(id);
            return new ResponseEntity<>(voluntario, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remover voluntário por ID",
            description = "Exclui os dados de um voluntário a partir do seu identificador único."
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            voluntarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Trata erros de validação de Jakarta Validation.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}