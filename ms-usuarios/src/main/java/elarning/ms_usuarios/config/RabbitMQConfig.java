package elarning.ms_usuarios.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Este é o nome da fila que o RabbitMQ irá criar
    public static final String QUEUE_USUARIO_NOVO = "q.usuario.novo";

    @Bean
    public Queue queueUsuarioNovo() {
        // true = a fila continuará existindo mesmo se o RabbitMQ reiniciar
        return new Queue(QUEUE_USUARIO_NOVO, true);
    }
}