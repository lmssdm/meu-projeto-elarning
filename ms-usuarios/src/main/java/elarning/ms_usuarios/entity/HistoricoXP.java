package elarning.ms_usuarios.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_xp")
public class HistoricoXP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "xp_ganho", nullable = false)
    private Integer xpGanho;

    @Column(nullable = false)
    private String motivo;

    @Column(name = "referencia_id")
    private String referenciaId;

    public HistoricoXP() {
    }

    public HistoricoXP(Funcionario funcionario, LocalDateTime dataHora, Integer xpGanho, String motivo, String referenciaId) {
        this.funcionario = funcionario;
        this.dataHora = dataHora;
        this.xpGanho = xpGanho;
        this.motivo = motivo;
        this.referenciaId = referenciaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getXpGanho() {
        return xpGanho;
    }

    public void setXpGanho(Integer xpGanho) {
        this.xpGanho = xpGanho;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getReferenciaId() {
        return referenciaId;
    }

    public void setReferenciaId(String referenciaId) {
        this.referenciaId = referenciaId;
    }
}