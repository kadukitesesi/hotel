package com.kadukitesesi.hotel.api.controller;


import com.kadukitesesi.hotel.api.exception.QuartoIndisponivelException;
import com.kadukitesesi.hotel.model.Quarto;
import com.kadukitesesi.hotel.model.Reserva;
import com.kadukitesesi.hotel.service.QuartoService;
import com.kadukitesesi.hotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Reserva criarReserva(@RequestBody Reserva reserva) throws Exception {
        try {
            if (reserva.getQuarto() != null && reserva.getQuarto().getId() != null) {
                Quarto quarto = quartoService.getQuartoById(reserva.getQuarto().getId());
                reserva.setQuarto(quarto);
            }

            Reserva reservaSalva = reservaService.salvarReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalva).getBody();

        } catch (QuartoIndisponivelException e) {
            throw new QuartoIndisponivelException("Quarto indisponivel no momento");
        } catch (Exception e) {
            throw new Exception("Não foi possivel criar sua reserva");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Reserva> reservaPorId(@PathVariable Long id) {
        Reserva reserva = reservaService.getReservaById(id);

        return reserva != null ? ResponseEntity.ok(reserva) :
                ResponseEntity.notFound().build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> atualiarParcialReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        try {
            Reserva reservaAtualizada = reservaService.atualizarParcialReserva(id, reserva);
            return ResponseEntity.ok(reservaAtualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> excluirReserva(@PathVariable Long id) {
        reservaService.excluirReservaById(id);
        return ResponseEntity.noContent().build();
    }

    

}
