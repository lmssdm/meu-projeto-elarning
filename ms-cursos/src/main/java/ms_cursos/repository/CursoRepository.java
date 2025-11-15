package ms_cursos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ms_cursos.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Para o R04 (Catálogo)
    List<Curso> findByAtivo(boolean ativo);

    // Para o R04 (Busca)
    List<Curso> findByAtivoTrueAndTituloContainingIgnoreCase(String titulo);

    // Para o R04 (Busca)
    List<Curso> findByAtivoTrueAndCategoriaNomeContainingIgnoreCase(String categoria);

    @Query("SELECT MAX(c.codigo) FROM Curso c")
    String findMaxCodigo();
}