package elarning.ms_usuarios.repository;

import elarning.ms_usuarios.entity.HistoricoXP;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoricoXPRepository extends JpaRepository<HistoricoXP, Long> {
    
    List<HistoricoXP> findByFuncionario_Cpf(String funcionarioCpf);
}