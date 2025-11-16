package elarning.ms_usuarios.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elarning.ms_usuarios.config.RabbitMQConfig;
import elarning.ms_usuarios.dto.FuncionarioCadastroDTO;
import elarning.ms_usuarios.dto.FuncionarioDashboardDTO;
import elarning.ms_usuarios.dto.UsuarioEmailDTO;
import elarning.ms_usuarios.entity.Departamento;
import elarning.ms_usuarios.entity.Funcionario;
import elarning.ms_usuarios.exception.FuncionarioJaExisteException;
import elarning.ms_usuarios.repository.DepartamentoRepository;
import elarning.ms_usuarios.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepo;

    @Autowired
    private DepartamentoRepository deptoRepo; 

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Lógica de registro (R01) - (Sem alterações, já estava correta)
    public void registrar(FuncionarioCadastroDTO dto) {
        
        if (funcionarioRepo.findByCpf(dto.getCpf()).isPresent() || 
            funcionarioRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new FuncionarioJaExisteException("CPF ou E-mail já cadastrado.");
        }

        Departamento depto = deptoRepo.findByCodigo(dto.getDepartamento())
                .orElseThrow(() -> new RuntimeException("Departamento com código '" + dto.getDepartamento() + "' não encontrado."));

        Funcionario funcionario = mapper.map(dto, Funcionario.class);
        funcionario.setDepartamentoId(depto.getId()); 
        funcionario.setXpTotal(0);
        funcionario.setNivel("Iniciante");
        funcionario.setStatus("ATIVO");
        funcionario.setDataCadastro(LocalDateTime.now());

        funcionarioRepo.save(funcionario);

        try {
            UsuarioEmailDTO emailDTO = new UsuarioEmailDTO(funcionario.getNome(), funcionario.getEmail());
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_USUARIO_NOVO, emailDTO);
            System.out.println("Mensagem de novo usuário enviada para a fila: " + emailDTO.getEmail());
        } catch (Exception e) {
            System.err.println("ERRO AO ENVIAR MENSAGEM PARA RABBITMQ: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --- NOVO MÉTODO ADICIONADO ---
    /**
     * Busca os dados para o Dashboard do Funcionário (R03)
     */
    public FuncionarioDashboardDTO getDashboard(String email) {
        Funcionario f = funcionarioRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado: " + email));

        // Mapeia os dados básicos do R03
        FuncionarioDashboardDTO dto = new FuncionarioDashboardDTO();
        dto.setNome(f.getNome());
        dto.setCargo(f.getCargo());
        dto.setXpTotal(f.getXpTotal());
        dto.setNivel(f.getNivel());
        
        // TODO: Futuramente (R03), você precisará chamar
        // o ms-progresso para buscar "cursos em andamento"
        // e o ms-gamificacao para buscar "ranking".
        // Por enquanto, isso atende o básico.
        
        return dto;
    }
}