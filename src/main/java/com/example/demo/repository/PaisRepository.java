package com.example.demo.repository;

import com.example.demo.domain.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    Pais findPaisByNome (String nome);
    Pais findPaisByRegiao (String regiao);
}
