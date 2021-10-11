package com.br.api.wine.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Cep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String codigoLoja;
    private long faixaInicio;
    private long faixaFim;

    public Cep() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoLoja() {
        return codigoLoja;
    }

    public void setCodigoLoja(String codigoLoja) {
        this.codigoLoja = codigoLoja;
    }

    public long getFaixaInicio() {
        return faixaInicio;
    }

    public void setFaixaInicio(long faixaInicio) {
        this.faixaInicio = faixaInicio;
    }

    public long getFaixaFim() {
        return faixaFim;
    }

    public void setFaixaFim(long faixaFim) {
        this.faixaFim = faixaFim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cep cep = (Cep) o;
        return faixaInicio == cep.faixaInicio && faixaFim == cep.faixaFim && Objects.equals(id, cep.id) && Objects.equals(codigoLoja, cep.codigoLoja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigoLoja, faixaInicio, faixaFim);
    }
}
