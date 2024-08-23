package com.kadukitesesi.hotel.service;

import com.kadukitesesi.hotel.model.Quarto;
import com.kadukitesesi.hotel.model.Reserva;
import com.kadukitesesi.hotel.repository.QuartoRepository;
import com.kadukitesesi.hotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class ReservaService {


    private ReservaRepository reservaRepository;

    private QuartoService quartoService;

    private QuartoRepository quartoRepository;



    public ReservaService(ReservaRepository reservaRepository, QuartoService quartoService, QuartoRepository quartoRepository) {
        this.reservaRepository = reservaRepository;
        this.quartoService = quartoService;
        this.quartoRepository = quartoRepository;
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Reserva salvarReserva(Reserva reserva) {

      Optional<Quarto> quarto =  quartoRepository.findById(reserva.getQuarto().getId());
        quartoService.deixarIndisponivel(quarto.get().getId());
        return reservaRepository.save(reserva);
    }

    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public void excluirReservaById(Long id) {
        reservaRepository.deleteById(id);
    }

    public BigDecimal calcularPrecoReserva(Long id) {
        Optional<Reserva> reservaBuscada = reservaRepository.findById(id);
        LocalDate chegada = reservaBuscada.get().getChegada();
        LocalDate saida = reservaBuscada.get().getSaida();
        Long diasHospedado = ChronoUnit.DAYS.between(chegada, saida);

        BigDecimal valorDiaria = reservaBuscada.get().getQuarto().getPreco();

        BigDecimal valorHospedagem = valorDiaria.multiply(BigDecimal.valueOf(diasHospedado));
        return valorHospedagem;
    }
}
