package com.estagio.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Estagiario {
    private final UUID id;

    @NotBlank

    private final String nome;
    private final String email;
    private final String telefone;
    private final String foto;
    private final String comprovanteMatricula;
    private final String interesses;
    private final String dominios;

    public Estagiario(@JsonProperty("id") UUID id,
                      @JsonProperty("nome") String nome,
                      @JsonProperty("email") String email,
                      @JsonProperty("telefone") String telefone,
                      @JsonProperty("foto") String foto,
                      @JsonProperty("comprovanteMatricula") String comprovanteMatricula,
                      @JsonProperty("interesses") String interesses,
                      @JsonProperty("dominios") String dominios) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.foto = foto;
        this.comprovanteMatricula = comprovanteMatricula;
        this.interesses = interesses;
        this.dominios = dominios;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getFoto() {
        return foto;
    }

    public String getComprovanteMatricula() {
        return comprovanteMatricula;
    }

    public String getInteresses() {
        return interesses;
    }

    public String getDominios() {
        return dominios;
    }
}
