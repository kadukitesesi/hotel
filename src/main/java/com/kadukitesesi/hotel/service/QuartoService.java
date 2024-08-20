package com.kadukitesesi.hotel.service;

import com.kadukitesesi.hotel.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadukitesesi.hotel.model.Quarto;

import java.util.List;

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

    public Quarto atualizarParcialQuarto(Long id, Quarto quartoParcial) throws Exception {
        Quarto quartoAtual = quartoRepository.findById(id)
                .orElseThrow(() -> new Exception("Quarto não encontrado!"));

        if (quartoParcial.getBanheiros() != 0) {
            quartoAtual.setBanheiros(quartoParcial.getBanheiros());
        }
        if (quartoParcial.getPreco() != null) {
            quartoAtual.setPreco(quartoParcial.getPreco());
        }
        if (quartoParcial.getNumeroDoQuarto() != 0) {
            quartoAtual.setNumeroDoQuarto(quartoParcial.getNumeroDoQuarto());
        }
        if (quartoParcial.getDisponibilidade() != null) {
            quartoAtual.setDisponibilidade(quartoParcial.getDisponibilidade());
        }

        return quartoRepository.save(quartoAtual);
    }
}
