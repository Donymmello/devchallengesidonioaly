package com.example.demo.service;

import com.example.demo.domain.Pais;
import com.example.demo.exception.domain.NomeJaExistenteException;
import com.example.demo.exception.domain.PaisNaoEncontradoException;

import java.util.List;

public interface PaisService {
    Pais registar(String nome, String capital, String regiao, String sub_regiao, String area) throws PaisNaoEncontradoException, NomeJaExistenteException;

    List<Pais> getPaises();

    Pais findPaisByNome(String nome);

    //Pais findPaisByregiao(String regiao);

    Pais actualizarPais(String current, String nome, String capital, String regiao, String sub_regiao, String area) throws PaisNaoEncontradoException, NomeJaExistenteException;

    void apagarPais(String nome);
}
