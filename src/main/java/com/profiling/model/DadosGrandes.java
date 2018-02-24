package com.profiling.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class DadosGrandes {
	@Id @GeneratedValue
    private Long id;
    private @NonNull String name;
    
    @Column(name = "coluna1")
    private String coluna1;
    
    @Column(name = "coluna2")
    private String coluna2;
    
    @Column(name = "coluna3")
    private String coluna3;
    
    @Column(name = "data_criacao")
    private Date data_criacao;
    
    @Column(name = "data_alteracao")
    private Date data_alteracao;
}
