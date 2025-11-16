package elarning.ms_usuarios.service;

// --- IMPORTS ADICIONADOS ---
import elarning.ms_usuarios.config.RabbitMQConfig;
import elarning.ms_usuarios.dto.UsuarioEmailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
// --- FIM DOS IMPORTS ADICIONADOS ---

import elarning.ms_usuarios.dto.FuncionarioCadastroDTO;
import elarning.ms_usuarios.entity.Funcionario;
import elarning.ms_usuarios.exception.FuncionarioJaExisteException;
import elarning.ms_usuarios.repository.FuncionarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepo;

    @Autowired
    private ModelMapper mapper;

    // <--- ADICIONAR (Injeta o template do RabbitMQ) ---
    @Autowired
    private RabbitTemplate rabbitTemplate;
    // --- FIM DA ADIÇÃO ---

    public void registrar(FuncionarioCadastroDTO dto) {
        
        // Validação (já estava correta)
        if (funcionarioRepo.findByCpf(dto.getCpf()).isPresent() || 
            funcionarioRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new FuncionarioJaExisteException("CPF ou E-mail já cadastrado.");
        }

        Funcionario funcionario = mapper.map(dto, Funcionario.class);

        // Define os padrões do R01
        funcionario.setXpTotal(0);
        funcionario.setNivel("Iniciante");
        funcionario.setStatus("ATIVO");
        funcionario.setDataCadastro(LocalDateTime.now());

        // 1. Salva o funcionário no banco de dados (já estava correto)
        funcionarioRepo.save(funcionario);

        // <--- ADICIONAR (Bloco de envio para o RabbitMQ) ---
        try {
            // 2. Cria o objeto da mensagem (o DTO que criamos)
            UsuarioEmailDTO emailDTO = new UsuarioEmailDTO(funcionario.getNome(), funcionario.getEmail());
            
            // 3. Envia a mensagem para a fila específica
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_USUARIO_NOVO, emailDTO);
            
            // Log para sabermos que funcionou
            System.out.println("Mensagem de novo usuário enviada para a fila: " + emailDTO.getEmail());

        } catch (Exception e) {
            // Se o RabbitMQ falhar, o cadastro não é desfeito (importante!)
            System.err.println("ERRO AO ENVIAR MENSAGEM PARA RABBITMQ: " + e.getMessage());
            e.printStackTrace();
        }
        // --- FIM DA ADIÇÃO ---
    }
}