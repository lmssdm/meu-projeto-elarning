package ms_cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ms_cursos.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}