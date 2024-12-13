package edu.ifam.msf.msf.voluntario;

import edu.ifam.msf.msf.voluntario.dto.VoluntarioInputDTO;
import edu.ifam.msf.msf.voluntario.dto.VoluntarioOutputDTO;
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
public class VoluntarioController {

    @Autowired
    private VoluntarioService voluntarioService;

    /**
     * Lista todos os voluntários.
     */
    @GetMapping
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
    public ResponseEntity<VoluntarioOutputDTO> create(@Valid @RequestBody VoluntarioInputDTO voluntarioInputDTO) {
        try {
            VoluntarioOutputDTO voluntarioOutputDTO = voluntarioService.create(voluntarioInputDTO);
            return new ResponseEntity<>(voluntarioOutputDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Atualiza os dados de um voluntário existente.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

    /**
     * Recupera um voluntário pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VoluntarioOutputDTO> getById(@PathVariable Long id) {
        try {
            VoluntarioOutputDTO voluntario = voluntarioService.getById(id);
            return new ResponseEntity<>(voluntario, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Remove um voluntário pelo ID.
     */
    @DeleteMapping("/{id}")
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

    /**
     * Trata exceções genéricas, retornando uma mensagem customizada.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("erro", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}