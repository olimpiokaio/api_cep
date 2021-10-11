package com.br.api.wine.controller.CepForm;

import com.br.api.wine.model.Cep;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CepForm implements Serializable {

    private Long id;
    @NotNull @NotEmpty
    private String codigoLoja;
    @NotNull
    private long faixaInicio;
    @NotNull
    private long faixaFim;

    public CepForm() {}

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

    public static Cep converter(CepForm cepForm) {
        Cep cep = new Cep();
        cep.setCodigoLoja(cepForm.getCodigoLoja());
        cep.setFaixaInicio(cepForm.getFaixaInicio());
        cep.setFaixaFim(cepForm.getFaixaFim());
        return cep;
    }
}
