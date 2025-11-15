package elarning.ms_usuarios.controller;

import elarning.ms_usuarios.dto.FuncionarioCadastroDTO;
import elarning.ms_usuarios.exception.FuncionarioJaExisteException;
import elarning.ms_usuarios.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarFuncionario(@RequestBody @Valid FuncionarioCadastroDTO dto) {
        try {
            funcionarioService.registrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (FuncionarioJaExisteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro: " + e.getMessage());
        }
    }
}