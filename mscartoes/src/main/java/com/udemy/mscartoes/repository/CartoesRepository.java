package com.udemy.mscartoes.repository;

import com.udemy.mscartoes.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartoesRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findByRendaLessThan(BigDecimal renda);
}
