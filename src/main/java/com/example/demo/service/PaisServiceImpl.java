package com.example.demo.service;

import com.example.demo.domain.Pais;
import com.example.demo.exception.domain.NomeJaExistenteException;
import com.example.demo.exception.domain.PaisNaoEncontradoException;
import com.example.demo.repository.PaisRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.demo.constant.PaisImplConstant.NENHUM_PAIS_ENCONTRADO;
import static com.example.demo.constant.PaisImplConstant.NOME_JA_EXISTE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
@Transactional
@Component
public class PaisServiceImpl implements PaisService {
    private final PaisRepository paisRepository;

    public PaisServiceImpl(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @Override
    public Pais registar(String nome, String capital, String regiao, String sub_regiao, String area) throws PaisNaoEncontradoException, NomeJaExistenteException {
        validateNewNome(EMPTY, nome);
        Pais pais = new Pais();
        pais.setNome(nome);
        pais.setCapital(capital);
        pais.setRegiao(regiao);
        pais.setSub_regiao(sub_regiao);
        pais.setArea(area);
        paisRepository.save(pais);
        return pais;
    }
    @Override
    public Pais actualizarPais(String currentNome, String newNome, String newCapital, String newRegiao, String newSub_regiao, String newArea) throws PaisNaoEncontradoException, NomeJaExistenteException {
        Pais currentPais = validateNewNome(currentNome, newNome);
        currentPais.setNome(newNome);
        currentPais.setCapital(newCapital);
        currentPais.setRegiao(newRegiao);
        currentPais.setSub_regiao(newSub_regiao);
        currentPais.setArea(newArea);
        paisRepository.save(currentPais);
        return currentPais;
    }
    @Override
    public List<Pais> getPaises() {
        return paisRepository.findAll();
    }

    @Override
    public Pais findPaisByNome(String nome) {
        return paisRepository.findPaisByNome(nome);
    }
    @Override
    public void apagarPais (String nome) {
        Pais pais = paisRepository.findPaisByNome(nome);
    }
    private Pais validateNewNome(String currentNome, String newNome) throws PaisNaoEncontradoException, NomeJaExistenteException {
        Pais paisByNewNome = findPaisByNome(newNome);
        if (StringUtils.isNotBlank(currentNome)) {
            Pais currentPais = findPaisByNome(currentNome);
            if (currentPais == null) {
                throw new PaisNaoEncontradoException(NENHUM_PAIS_ENCONTRADO + currentNome);
            }
            if ((paisByNewNome != null) && !currentPais.getId().equals(paisByNewNome.getId())) {
                throw new NomeJaExistenteException(NOME_JA_EXISTE);
            }

            return currentPais;
        } else {
            if (paisByNewNome != null) {
                throw new NomeJaExistenteException(NOME_JA_EXISTE);
            }

            return null;
        }

    }
}
