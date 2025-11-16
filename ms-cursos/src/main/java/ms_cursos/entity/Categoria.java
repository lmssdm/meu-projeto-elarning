package ms_cursos.entity;

import jakarta.persistence.Column; // Importe esta classe
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // --- CORREÇÃO AQUI ---
    @Column(unique = true) // Adicione esta linha
    private String codigo; // Ex: TECH, LIDER
    // --- FIM DA CORREÇÃO ---

    private String nome;
    private String descricao;
    
    @Column(name = "cor_hex")
    private String corHex;

    // Getters e Setters... (o resto do arquivo fica igual)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getCorHex() { return corHex; }
    public void setCorHex(String corHex) { this.corHex = corHex; }
}