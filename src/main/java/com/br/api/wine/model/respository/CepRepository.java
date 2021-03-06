package com.br.api.wine.model.respository;

import com.br.api.wine.model.Cep;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {

    @Query("SELECT c FROM Cep c WHERE c.faixaInicio <= ?1")
    List<Cep> buscarPorFaixaInicial(long cep, Pageable paginacao);

    @Query("SELECT c FROM Cep c")
    List<Cep> buscarPrimeiroCep(Pageable paginacao);

    @Query("SELECT c FROM Cep c WHERE c.faixaInicio <= ?1 and c.id <> ?2")
    List<Cep> buscarPorFaixaInicialDiferenteDe(long faixa, Long id, Pageable paginacao);

    @Query("SELECT c FROM Cep c WHERE (c.faixaInicio >= ?1 and c.faixaInicio <= ?2) or (c.faixaFim <= ?1 and c.faixaInicio >= ?2)")
    List<Cep> buscarCepIntermediario(long faixaIncio, long faixaFim, Pageable paginacao);

    @Query("SELECT c FROM Cep c WHERE ((c.faixaInicio >= ?1 and c.faixaInicio <= ?2) or (c.faixaFim <= ?1 and c.faixaInicio >= ?2)) and c.id <> ?3")
    List<Cep> buscarCepIntermediarioComExecao(long faixaIncio, long faixaFim, Long id, Pageable paginacao);
}





















