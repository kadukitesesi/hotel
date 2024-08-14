package com.kadukitesesi.hotel.controller;


import com.kadukitesesi.hotel.model.Quarto;
import com.kadukitesesi.hotel.model.Reserva;
import com.kadukitesesi.hotel.service.QuartoService;
import com.kadukitesesi.hotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.listarReservas();
    }

    @PostMapping
    Reserva criarReserva(@RequestBody Reserva reserva) {
        if (reserva.getQuarto() != null && reserva.getQuarto().getId() != null) {
            Quarto quarto = quartoService.getQuartoById(reserva.getQuarto().getId());
            reserva.setQuarto(quarto);
        }

        Reserva reservaSalva = reservaService.salvarReserva(reserva);
        return ResponseEntity.ok(reservaSalva).getBody();
    }

    @GetMapping("/{id}")
    ResponseEntity<Reserva> reservaPorId(@PathVariable Long id) {
        Reserva reserva = reservaService.getReservaById(id);

        return reserva != null ? ResponseEntity.ok(reserva) :
                ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    ResponseEntity<Reserva> editarReserva(@PathVariable Long id, Reserva reserva) {
        Reserva reservaAtual = reservaService.getReservaById(id);
        if (reservaAtual == null) {
            throw new IllegalArgumentException("Quarto buscado inválido");
        }

        reservaService.salvarReserva(reserva);

        return ResponseEntity.ok().build();

    }


    @DeleteMapping("/{id}")
    ResponseEntity<Reserva> excluirReserva(@PathVariable Long id) {
        reservaService.excluirReservaById(id);
        return ResponseEntity.noContent().build();
    }

    

}
