package ru.shcheglov.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "app_currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "general-generator")
    @SequenceGenerator(name = "general-generator", sequenceName = "SQ_GENERAL", allocationSize = 1)
    @Column(name = "CURRENCY_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "NUM_CODE", nullable = false)
    private Integer numCode;

    @Column(name = "CHAR_CODE", nullable = false)
    private String charCode;

    @Column(name = "NOMINAL", nullable = false)
    private Integer nominal;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "VALUE", nullable = false)
    private Double value;
}
