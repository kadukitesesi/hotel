package com.kadukitesesi.hotel.service;


import com.kadukitesesi.hotel.model.Quarto;
import com.kadukitesesi.hotel.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Quarto atualizarParcialquarto(Long id, Quarto quartoParcial) throws Exception {
        Quarto quartoAtual = quartoRepository.findById(id)
                .orElseThrow(() -> new Exception("quarto não encontrada!"));

        if (quartoAtual.getPreco() != null) {
            quartoAtual.setPreco(quartoParcial.getPreco());
        }
        if (quartoParcial.getId() != null) {
            quartoAtual.setId(quartoParcial.getId());
        }

        if (quartoAtual.getDisponibilidade() != null) {
            quartoAtual.setDisponibilidade(quartoParcial.getDisponibilidade());
        }


        return quartoRepository.save(quartoAtual);
    }
}
