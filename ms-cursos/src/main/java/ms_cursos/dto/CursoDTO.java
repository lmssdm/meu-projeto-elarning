package ms_cursos.dto;

public class CursoDTO {

    private String titulo;
    private String descricao;
    private Integer duracaoEstimada;
    private Integer xpOferecido;
    private String nivelDificuldade;
    private String categoriaNome;

    // Construtor vazio
    public CursoDTO() {}

    // Construtor para facilitar
    public CursoDTO(String titulo, String descricao, Integer duracaoEstimada, Integer xpOferecido, String nivelDificuldade, String categoriaNome) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracaoEstimada = duracaoEstimada;
        this.xpOferecido = xpOferecido;
        this.nivelDificuldade = nivelDificuldade;
        this.categoriaNome = categoriaNome;
    }

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getDuracaoEstimada() { return duracaoEstimada; }
    public void setDuracaoEstimada(Integer duracaoEstimada) { this.duracaoEstimada = duracaoEstimada; }
    public Integer getXpOferecido() { return xpOferecido; }
    public void setXpOferecido(Integer xpOferecido) { this.xpOferecido = xpOferecido; }
    public String getNivelDificuldade() { return nivelDificuldade; }
    public void setNivelDificuldade(String nivelDificuldade) { this.nivelDificuldade = nivelDificuldade; }
    public String getCategoriaNome() { return categoriaNome; }
    public void setCategoriaNome(String categoriaNome) { this.categoriaNome = categoriaNome; }
}