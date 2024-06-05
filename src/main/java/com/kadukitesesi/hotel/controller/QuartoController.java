package com.kadukitesesi.hotel.controller;


import com.kadukitesesi.hotel.model.Quarto;
import com.kadukitesesi.hotel.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    @Autowired
    QuartoService quartoService;

    @GetMapping
    public List<Quarto> listarQuartos() {
        return quartoService.listarQuartos();
    }


    @PostMapping
    public Quarto criarQuarto(@RequestBody Quarto quarto) {
        return quartoService.salvarQuarto(quarto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quarto> buscarQuarto( @PathVariable Long id) {
        Quarto quarto = quartoService.getQuartoById(id);
        return quarto != null ? ResponseEntity.ok(quarto)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quarto> excluirQuarto(@PathVariable Long id) {
        quartoService.excluirQuartoById(id);
        return ResponseEntity.noContent().build();
    }


}
