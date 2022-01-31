package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "paises")
public class Pais {
    private static final long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String nome;
    private String capital;
    private String regiao;
    private String sub_regiao;
    private String area;

    public Pais() {
        this.nome = nome;
        this.capital = capital;
        this.regiao = regiao;
        this.sub_regiao = sub_regiao;
        this.area = area;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getSub_regiao() {
        return sub_regiao;
    }

    public void setSub_regiao(String sub_regiao) {
        this.sub_regiao = sub_regiao;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}

