package ms_cursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ms_cursos.dto.CursoDTO;
import ms_cursos.dto.CursoInstrutorDTO;
import ms_cursos.entity.Curso;
import ms_cursos.service.CursoService;

@RestController
@RequestMapping("/cursos") // Rota base
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Endpoint para R04: Catálogo de Cursos (para Funcionários)
    @GetMapping("/catalogo")
    public ResponseEntity<List<CursoDTO>> getCatalogo() {
        return ResponseEntity.ok(cursoService.listarCursosDisponiveis());
    }

    // Endpoint para R04: Busca por palavra-chave (para Funcionários)
    @GetMapping("/buscar")
    public ResponseEntity<List<CursoDTO>> buscarCursos(@RequestParam String termo) {
        return ResponseEntity.ok(cursoService.buscarCursos(termo));
    }

    // Endpoint para R12: Criar Curso (para Instrutores)
    @PostMapping
    public ResponseEntity<?> criarCurso(@RequestBody CursoInstrutorDTO dto) {
        try {
            Curso novoCurso = cursoService.criarCurso(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Aqui virão os outros endpoints do R12 e R13 (Editar, Ativar, Adicionar Módulo, etc.)
}