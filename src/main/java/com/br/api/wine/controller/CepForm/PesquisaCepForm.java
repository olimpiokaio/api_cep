package com.br.api.wine.controller.CepForm;

import javax.validation.constraints.NotNull;

public class PesquisaCepForm {

    private long cep;

    public long getCep() {
        return cep;
    }

    public void setCep(long cep) {
        this.cep = cep;
    }
}
