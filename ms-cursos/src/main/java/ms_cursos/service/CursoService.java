package ms_cursos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms_cursos.dto.CursoDTO;
import ms_cursos.dto.CursoInstrutorDTO;
import ms_cursos.entity.Categoria;
import ms_cursos.entity.Curso;
import ms_cursos.repository.CategoriaRepository;
import ms_cursos.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Implementação do R04: Catálogo de Cursos
    public List<CursoDTO> listarCursosDisponiveis() {
        return cursoRepository.findByAtivo(true).stream()
                .map(this::mapCursoToCursoDTO)
                .collect(Collectors.toList());
    }

    // Implementação do R04: Busca por palavra-chave
    public List<CursoDTO> buscarCursos(String termo) {
        // Esta é uma busca simples. O PDF pede filtros mais complexos
        List<Curso> cursos = cursoRepository.findByAtivoTrueAndTituloContainingIgnoreCase(termo);
        return cursos.stream()
                .map(this::mapCursoToCursoDTO)
                .collect(Collectors.toList());
    }

    // Implementação do R12: Gerenciar Cursos (Criar)
    public Curso criarCurso(CursoInstrutorDTO dto) {

        // 1. Encontra a Categoria pelo código (ex: "TECH")
        Categoria categoria = categoriaRepository.findByCodigo(dto.getCategoriaCodigo())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + dto.getCategoriaCodigo()));

        // 2. Mapeia o DTO para a Entidade
        Curso novoCurso = new Curso();
        BeanUtils.copyProperties(dto, novoCurso);
        novoCurso.setCategoria(categoria);

        // 3. Define valores padrão
        novoCurso.setCodigo(gerarProximoCodigoCurso());
        novoCurso.setAtivo(false); // R12: Cursos começam como RASCUNHO (Inativos)

        return cursoRepository.save(novoCurso);
    }

    // --- Métodos Auxiliares ---

    private CursoDTO mapCursoToCursoDTO(Curso curso) {
        return new CursoDTO(
                curso.getTitulo(),
                curso.getDescricao(),
                curso.getDuracaoEstimada(),
                curso.getXpOferecido(),
                curso.getNivelDificuldade(),
                curso.getCategoria() != null ? curso.getCategoria().getNome() : "Sem Categoria"
        );
    }

    private String gerarProximoCodigoCurso() {
        String maxCodigo = cursoRepository.findMaxCodigo();
        int proximoNumero = 1;
        if (maxCodigo != null && maxCodigo.startsWith("CUR")) {
            try {
                proximoNumero = Integer.parseInt(maxCodigo.substring(3)) + 1;
            } catch (NumberFormatException e) {
                // Ignora, usa o padrão 1
            }
        }
        return "CUR" + String.format("%03d", proximoNumero);
    }
}