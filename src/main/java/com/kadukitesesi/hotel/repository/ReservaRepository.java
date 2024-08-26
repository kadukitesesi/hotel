package com.kadukitesesi.hotel.repository;

import com.kadukitesesi.hotel.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.quarto.id = :quartoId AND " +
            "(r.chegada <= :saida AND r.saida >= :chegada)")
    List<Reserva> findReservasByQuartoIdAndDatas(@Param("quartoId") Long quartoId,
                                                 @Param("chegada") LocalDate chegada,
                                                 @Param("saida") LocalDate saida);

}
