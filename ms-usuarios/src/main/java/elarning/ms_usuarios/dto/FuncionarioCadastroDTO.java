package elarning.ms_usuarios.dto;

public class FuncionarioCadastroDTO {
    
    private String cpf;
    private String nome;
    private String email; // <--- ADICIONE ESTA LINHA
    private String departamento;
    private String cargo;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // --- ADICIONE ESTES GETTERS E SETTERS ---
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // --- FIM DA ADIÇÃO ---

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}