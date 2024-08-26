package com.kadukitesesi.hotel.repository;

import com.kadukitesesi.hotel.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface QuartoRepository extends JpaRepository<Quarto, Long> {


    Optional<Quarto> findByNumeroDoQuarto(long numeroDoQuarto);
}
