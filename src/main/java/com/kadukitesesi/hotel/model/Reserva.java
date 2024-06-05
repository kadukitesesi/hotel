package com.kadukitesesi.hotel.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDaReserva;
    private LocalDate chegada;
    private LocalDate saida;

    @ManyToOne
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDaReserva() {
        return nomeDaReserva;
    }

    public void setNomeDaReserva(String nomeDaReserva) {
        this.nomeDaReserva = nomeDaReserva;
    }

    public LocalDate getChegada() {
        return chegada;
    }

    public void setChegada(LocalDate chegada) {
        this.chegada = chegada;
    }

    public LocalDate getSaida() {
        return saida;
    }

    public void setSaida(LocalDate saida) {
        this.saida = saida;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
}
