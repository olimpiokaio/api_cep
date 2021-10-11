package com.br.api.wine.controller.CepDto;

import com.br.api.wine.model.Cep;

import java.io.Serializable;

public class CepDto implements Serializable {

    private Long id;
    private String codigoLoja;
    private long faixaInicio;
    private long faixaFim;

    public CepDto() {}

    public CepDto(Cep cep) {
        this.codigoLoja = cep.getCodigoLoja();
        this.id = cep.getId();
        this.faixaInicio = cep.getFaixaInicio();
        this.faixaFim = cep.getFaixaFim();
    }

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
}
