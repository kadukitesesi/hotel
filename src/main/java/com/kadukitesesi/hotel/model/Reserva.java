package com.kadukitesesi.hotel.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDaReserva;
    private LocalDate chegada;
    private LocalDate saida;

    @Transient
    private BigDecimal valorHospedagem;

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

    public BigDecimal calcularValorHospedagem() {
        LocalDate chegada = getChegada();
        LocalDate saida = getSaida();
        long diasHospedado = ChronoUnit.DAYS.between(chegada, saida);
        BigDecimal valorDiaria = this.quarto.getPreco();
        this.valorHospedagem = valorDiaria.multiply(BigDecimal.valueOf(diasHospedado));
        return this.valorHospedagem;
    }

    public BigDecimal getValorHospedagem() {
        if (this.valorHospedagem == null) {
            calcularValorHospedagem();
        }
        return this.valorHospedagem;
    }
}
