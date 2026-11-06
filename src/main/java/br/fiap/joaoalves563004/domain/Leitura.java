package br.fiap.joaoalves563004.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "GS004_LEITURA")
@Getter @Setter
@NoArgsConstructor
public class Leitura {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRECHO_ID")
    private Trecho trecho;

    @Column(name = "DATA_HORA")
    private LocalDateTime dataHora;

    @Column(name = "LAMINA_CM")
    private Integer laminaCm;

    @Column(name = "STATUS")
    private String status;
}
