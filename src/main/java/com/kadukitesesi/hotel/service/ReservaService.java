package com.kadukitesesi.hotel.service;

import com.kadukitesesi.hotel.model.Quarto;
import com.kadukitesesi.hotel.model.Reserva;
import com.kadukitesesi.hotel.repository.QuartoRepository;
import com.kadukitesesi.hotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    private QuartoRepository quartoRepository;

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

    public Quarto getQuartoById(Long id) {
        return quartoRepository.findById(id).orElse(null);
    }
}
