package ms_cursos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ms_cursos.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByCodigo(String codigo);
}