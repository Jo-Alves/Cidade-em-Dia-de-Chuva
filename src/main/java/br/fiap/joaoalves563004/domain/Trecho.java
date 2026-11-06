package br.fiap.joaoalves563004.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GS004_TRECHO")
@Getter @Setter
@NoArgsConstructor
public class Trecho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BAIRRO_ID")
    private Bairro bairro;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "TIPO_CALCADA")
    private String tipoCalcada;

    @Column(name = "ALTITUDE")
    private Integer altitude;
}
