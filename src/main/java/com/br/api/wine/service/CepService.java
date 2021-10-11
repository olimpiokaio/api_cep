package com.br.api.wine.service;

import com.br.api.wine.controller.CepDto.CepDto;
import com.br.api.wine.controller.CepForm.CepForm;
import com.br.api.wine.controller.CepForm.PesquisaCepForm;
import com.br.api.wine.model.Cep;
import com.br.api.wine.model.respository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CepService {

    private final CepRepository cepRepository;

    @Autowired
    public CepService(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }

    public CepDto salvar(CepForm cepForm) {
        if (cepForm.getFaixaFim() > cepForm.getFaixaInicio()) {

            boolean faixaInicio = this.faixaJaRelacinada(cepForm.getFaixaInicio());
            boolean faixaFim = this.faixaJaRelacinada(cepForm.getFaixaFim());

            if (!(faixaInicio || faixaFim)) {
                Pageable paginacao = PageRequest.of(0, 1);
                List<Cep> list = cepRepository.buscarCepIntermediario(cepForm.getFaixaInicio(), cepForm.getFaixaFim(), paginacao);
                if(!(list != null && list.size() > 0)) {
                    Cep cep = CepForm.converter(cepForm);
                    Cep cepCadastrado = cepRepository.save(cep);
                    return new CepDto(cepCadastrado);
                }
            }
        }
        throw new IllegalArgumentException("Faixas de CEP já relacionadas");
    }

    public CepDto atualizar(CepForm cepForm) {
        Optional<Cep> optional = cepRepository.findById(cepForm.getId());

        if (optional.isPresent() && cepForm.getFaixaFim() > cepForm.getFaixaInicio()) {
            Cep cep = optional.get();

            boolean faixaInicio = this.podeAtualizarFaixa(cep.getId(), cepForm.getFaixaInicio());
            boolean faixaFim = this.podeAtualizarFaixa(cep.getId(), cepForm.getFaixaFim());

//            if (faixaInicio && faixaFim) {
//                cep.setFaixaInicio(cepForm.getFaixaInicio());
//                cep.setFaixaFim(cepForm.getFaixaFim());
//                cep.setCodigoLoja(cepForm.getCodigoLoja());
//                cepRepository.save(cep);
//                return new CepDto(cep);
//            }

            if (faixaInicio && faixaFim) {
                System.out.println("Passou");
                Pageable paginacao = PageRequest.of(0, 1);
                List<Cep> list = cepRepository.buscarCepIntermediarioComExecao(cepForm.getFaixaInicio(), cepForm.getFaixaFim(), cep.getId(), paginacao);
                if(!(list != null && list.size() > 0)) {
                    cep.setFaixaInicio(cepForm.getFaixaInicio());
                    cep.setFaixaFim(cepForm.getFaixaFim());
                    cep.setCodigoLoja(cepForm.getCodigoLoja());
                    cepRepository.save(cep);
                    return new CepDto(cep);
                }
            }

        }
        throw new IllegalArgumentException("Faixas de CEP já relacionadas");
    }

    public CepDto bucarCep(PesquisaCepForm cepForm) {
        Cep cep = this.buscarPorFaixaInicial(cepForm.getCep());

        if (cep != null && cep.getFaixaFim() >= cepForm.getCep()) {
            return new CepDto(cep);
        }
        return null;
    }

    private Cep buscarPorFaixaInicial(Long cepFaixa) {
        Pageable paginacao = PageRequest.of(0, 1, Sort.Direction.DESC, "faixaInicio");
        List<Cep> cepLista = cepRepository.buscarPorFaixaInicial(cepFaixa, paginacao);
        if (cepLista != null && cepLista.size() > 0) {
            return cepLista.get(0);
        }
        return null;
    }

    private Cep buscarPrimeiroCep() {
        Pageable paginacao = PageRequest.of(0, 1, Sort.Direction.ASC, "faixaInicio");
        List<Cep> cepLista = cepRepository.buscarPrimeiroCep(paginacao);
        if (cepLista != null && cepLista.size() > 0) {
            return cepLista.get(0);
        }
        return null;
    }

    private boolean faixaJaRelacinada(long faixa) {
        Cep cep = this.buscarPorFaixaInicial(faixa);
        if (cep != null && (cep.getFaixaFim() >= faixa)) {
            return true;
        } else {
            return false;
        }
    }

//    private boolean faixaFimJaRelacinada(long faixa) {
//        Cep cep = this.buscarPorFaixaInicial(faixa);
//        if (cep == null) {
//            return false;
//        }
//        if (cep.getFaixaFim() >= faixa) {
//            return true;
//        } else {
//            Cep primeiroCep = this.buscarPrimeiroCep();
//            if (faixa < primeiroCep.getFaixaInicio()) {
//                return false;
//            }
//            return true;
//        }
//    }

    public void remover(Long id) {
        Optional<Cep> optional = cepRepository.findById(id);
        if (optional.isPresent()) {
            cepRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("ID não encontrado");
        }
    }

    private boolean podeAtualizarFaixa(Long id, long faixa) {
        Pageable paginacao = PageRequest.of(0, 1, Sort.Direction.DESC, "faixaInicio");
        List<Cep> cepList = cepRepository.buscarPorFaixaInicialDiferenteDe(faixa, id, paginacao);

        if (cepList != null && cepList.size() > 0) {
            Cep cep = cepList.get(0);
            if (cep.getFaixaFim() >= faixa) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}
