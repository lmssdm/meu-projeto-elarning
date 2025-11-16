package elarning.ms_usuarios.controller;

import elarning.ms_usuarios.dto.FuncionarioCadastroDTO;
import elarning.ms_usuarios.exception.FuncionarioJaExisteException;
import elarning.ms_usuarios.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// --- IMPORTS ADICIONADOS ---
import elarning.ms_usuarios.dto.FuncionarioDashboardDTO;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.Map; // Para padronizar erros

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // R01: Autocadastro (Público)
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarFuncionario(@RequestBody @Valid FuncionarioCadastroDTO dto) {
        try {
            funcionarioService.registrar(dto);
            // Retorna 201 Created com o DTO
            return ResponseEntity.status(HttpStatus.CREATED).body(dto); 
        } catch (FuncionarioJaExisteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", e.getMessage()));
        } catch (Exception e) {
            // Se o depto não existir, etc.
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    // --- NOVA ROTA ADICIONADA ---
    // R03: Dashboard do Funcionário (Protegido)
    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard(
            // Recebe o email que o Gateway extraiu do token
            @RequestHeader("X-User-Email") String email) {
        try {
            FuncionarioDashboardDTO dashboard = funcionarioService.getDashboard(email);
            return ResponseEntity.ok(dashboard);
        } catch (Exception e) {
            // Ex: Funcionário não encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", e.getMessage()));
        }
    }
}