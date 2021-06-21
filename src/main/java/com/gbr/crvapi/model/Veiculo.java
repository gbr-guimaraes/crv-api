package com.gbr.crvapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class Veiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long veiculoId;

    @Column(nullable = false )
    private String marca;

    @Column(nullable = false )
    private String modelo;

    @Column(nullable = false )
    private String ano;

    @Column(nullable = false)
    private String valor;

    @Column(nullable = false)
    private String cpfProp;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

}

