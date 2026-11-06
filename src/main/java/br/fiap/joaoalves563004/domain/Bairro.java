package br.fiap.joaoalves563004.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.NumericBooleanConverter;

@Entity
@Table(name = "GS004_BAIRRO")
@Getter @Setter
@NoArgsConstructor
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "ZONA")
    private String zona;

    @Column(name = "POSSUI_PISCINAO")
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean possuiPiscinao;
}
