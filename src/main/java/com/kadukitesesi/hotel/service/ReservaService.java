package com.kadukitesesi.hotel.service;

import com.kadukitesesi.hotel.model.Reserva;
import com.kadukitesesi.hotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Reserva salvarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public void excluirQuartoById(Long id) {
        reservaRepository.deleteById(id);
    }
}
