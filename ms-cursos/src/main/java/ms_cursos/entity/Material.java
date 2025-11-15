package ms_cursos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "materiais")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;
    
    @Column(name = "nome_arquivo")
    private String nomeArquivo;
    
    @Column(name = "tipo_arquivo")
    private String tipoArquivo; // PDF, VIDEO, URL
    
    @Column(name = "url_storage")
    private String urlStorage;
    
    private Long tamanho;
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Modulo getModulo() { return modulo; }
    public void setModulo(Modulo modulo) { this.modulo = modulo; }
    public String getNomeArquivo() { return nomeArquivo; }
    public void setNomeArquivo(String nomeArquivo) { this.nomeArquivo = nomeArquivo; }
    public String getTipoArquivo() { return tipoArquivo; }
    public void setTipoArquivo(String tipoArquivo) { this.tipoArquivo = tipoArquivo; }
    public String getUrlStorage() { return urlStorage; }
    public void setUrlStorage(String urlStorage) { this.urlStorage = urlStorage; }
    public Long getTamanho() { return tamanho; }
    public void setTamanho(Long tamanho) { this.tamanho = tamanho; }
}