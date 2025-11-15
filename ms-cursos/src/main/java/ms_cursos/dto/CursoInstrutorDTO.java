package ms_cursos.dto;

public class CursoInstrutorDTO {

    private String titulo;
    private String descricao;
    private String categoriaCodigo; // O Instrutor informa o CÓDIGO da Categoria (ex: "TECH")
    private Long instrutorId; // O ID dele (virá do JWT no futuro)
    private Integer duracaoEstimada;
    private Integer xpOferecido;
    private String nivelDificuldade;
    
    // Lista de pré-requisitos (simplificado, por enquanto)
    // private List<String> preRequisitos; 

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getCategoriaCodigo() { return categoriaCodigo; }
    public void setCategoriaCodigo(String categoriaCodigo) { this.categoriaCodigo = categoriaCodigo; }
    public Long getInstrutorId() { return instrutorId; }
    public void setInstrutorId(Long instrutorId) { this.instrutorId = instrutorId; }
    public Integer getDuracaoEstimada() { return duracaoEstimada; }
    public void setDuracaoEstimada(Integer duracaoEstimada) { this.duracaoEstimada = duracaoEstimada; }
    public Integer getXpOferecido() { return xpOferecido; }
    public void setXpOferecido(Integer xpOferecido) { this.xpOferecido = xpOferecido; }
    public String getNivelDificuldade() { return nivelDificuldade; }
    public void setNivelDificuldade(String nivelDificuldade) { this.nivelDificuldade = nivelDificuldade; }
}