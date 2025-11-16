package elarning.ms_usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import elarning.ms_usuarios.entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
    // Permite buscar um departamento pelo seu código (ex: "TI")
    Optional<Departamento> findByCodigo(String codigo);
}