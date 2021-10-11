package com.br.api.wine.controller;

import com.br.api.wine.controller.CepDto.CepDto;
import com.br.api.wine.controller.CepForm.CepForm;
import com.br.api.wine.controller.CepForm.PesquisaCepForm;
import com.br.api.wine.model.Cep;
import com.br.api.wine.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping("/api/cep")
public class CepController {

    private final CepService cepService;

    @Autowired
    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CepDto> salvarCep(@RequestBody @Valid CepForm cepForm, UriComponentsBuilder uriBuilder) {
        try {
            CepDto cepDto = cepService.salvar(cepForm);
            URI uri = uriBuilder.path("/cep/{id}").buildAndExpand(cepDto.getId()).toUri();
            return ResponseEntity.created(uri).body(cepDto);
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid CepForm form) {
        try {
            CepDto cepDto = cepService.atualizar(form);
            return ResponseEntity.ok(cepDto);
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<CepDto> buscarPorCep(@RequestBody @Valid PesquisaCepForm cepForm) {
        try {
            return ResponseEntity.ok(cepService.bucarCep(cepForm));
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerCep(@PathVariable Long id) {
        try {
            cepService.remover(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

}
