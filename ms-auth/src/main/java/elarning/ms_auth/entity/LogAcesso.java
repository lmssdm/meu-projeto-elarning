package elarning.ms_auth.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "logs_acesso")
public class LogAcesso {
    
    @Id
    private String id;
    
    @Field(name = "usuario_id")
    private String usuarioId; 

    @Field(name = "data_hora")
    private LocalDateTime dataHora;

    @Field(name = "ip")
    private String ip;

    @Field(name = "user_agent")
    private String userAgent;

    public LogAcesso(String usuarioId, LocalDateTime dataHora, String ip, String userAgent) {
        this.usuarioId = usuarioId;
        this.dataHora = dataHora;
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}