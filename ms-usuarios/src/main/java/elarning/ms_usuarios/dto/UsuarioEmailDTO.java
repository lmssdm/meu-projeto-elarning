package elarning.ms_usuarios.dto;

import java.io.Serializable;

// Serializable é importante para permitir que o Spring converta
// o objeto em dados para enviar pela fila.
public class UsuarioEmailDTO implements Serializable {
    
    private String nome;
    private String email;

    // Construtor padrão é importante para a desserialização
    public UsuarioEmailDTO() {
    }

    public UsuarioEmailDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}