package ms_cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ms_cursos.entity.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {
}