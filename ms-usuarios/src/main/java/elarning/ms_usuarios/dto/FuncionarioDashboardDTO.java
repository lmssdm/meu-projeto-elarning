package elarning.ms_usuarios.dto;

// DTO para o R03: Dashboard do Funcionário
public class FuncionarioDashboardDTO {

    private String nome;
    private String cargo;
    private Integer xpTotal;
    private String nivel;
    
    // TODO: Adicionar futuramente (R03):
    // private String proximoBadge;
    // private List<CursoProgressoDTO> cursosEmAndamento;
    // private List<CursoProgressoDTO> cursosConcluidos;
    // private Integer rankingDepartamento;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public Integer getXpTotal() { return xpTotal; }
    public void setXpTotal(Integer xpTotal) { this.xpTotal = xpTotal; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
}