package elarning.ms_auth.listener;

import elarning.ms_auth.dto.UsuarioEmailDTO;
import elarning.ms_auth.entity.Usuario;
import elarning.ms_auth.repository.UsuarioRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.stream.Collectors;

@Component
public class CadastroListener {

    private static final Log LOG = LogFactory.getLog(CadastroListener.class);

    // Nome da fila que o ms-usuarios criou
    public static final String QUEUE_USUARIO_NOVO = "q.usuario.novo";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Bean que já existe no SecurityConfig [cite: 78]

    // (Opcional: Para o Passo 3 da SAGA - notificação)
    // @Autowired
    // private RabbitTemplate rabbitTemplate;

    // Esta anotação faz o método "ouvir" a fila
    @RabbitListener(queues = QUEUE_USUARIO_NOVO)
    public void onUsuarioNovo(UsuarioEmailDTO dto) {
        LOG.info("Recebida mensagem de novo usuário da fila: " + dto.getEmail());

        // 1. Gera a senha numérica de 6 dígitos (Requisito R01)
        String senhaPura = new SecureRandom().ints(0, 10)
                                            .limit(6)
                                            .mapToObj(String::valueOf)
                                            .collect(Collectors.joining());

        // 2. Cria a nova entidade Usuario para o MongoDB
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(senhaPura)); // Salva a senha hasheada
        novoUsuario.setTipo("FUNCIONARIO"); // Define o tipo
        novoUsuario.setStatus("ATIVO"); // Define o status (R01)
        novoUsuario.setUltimoAcesso(null);

        try {
            // 3. Salva o usuário de login no MongoDB
            usuarioRepository.save(novoUsuario);
            LOG.info("Usuário de login salvo no MongoDB: " + dto.getEmail());

            // IMPORTANTE: Log para você poder testar o login
            LOG.warn("SENHA GERADA PARA " + dto.getEmail() + ": " + senhaPura);

            // TODO: Futuramente, enviar para o ms-notificacoes
            // (Aqui você enviaria a 'senhaPura' para a fila de e-mail)

        } catch (Exception e) {
            // Se o e-mail já existir no Mongo (duplicidade)
            LOG.error("Erro ao salvar usuário vindo do RabbitMQ: " + e.getMessage());
        }
    }
}