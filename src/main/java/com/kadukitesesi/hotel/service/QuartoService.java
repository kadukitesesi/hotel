package com.kadukitesesi.hotel.service;


import com.kadukitesesi.hotel.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadukitesesi.hotel.model.Quarto;

import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    public List<Quarto> listarQuartos() {
        return quartoRepository.findAll();
    }

    public Quarto salvarQuarto(Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    public Quarto getQuartoById(Long id) {
        return quartoRepository.findById(id).orElse(null);
    }

    public void excluirQuartoById(Long id) {
        quartoRepository.deleteById(id);
    }

    public void deixarIndisponivel(Long id) {
        Optional<Quarto> quartoBuscado = quartoRepository.findById(id);
        quartoBuscado.get().setDisponibilidade(false);
    }

    public void deixarDisponivel(Long id) {
        Optional<Quarto> quartoBuscado = quartoRepository.findById(id);
        quartoBuscado.get().setDisponibilidade(true);
    }
}
