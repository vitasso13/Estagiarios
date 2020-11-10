package com.estagio.backend.dao;

import com.estagio.backend.model.Estagiario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstagiarioDao {
    int insertEstagiario(UUID id, Estagiario estagiario);

    default int insertEstagiario(Estagiario estagiario){
        UUID id = UUID.randomUUID();
        return insertEstagiario(id, estagiario);
    }

    List<Estagiario> selectAllPeople();

    Optional<Estagiario> selectEstagiarioById(UUID id);

    int deleteEstagiarioById(UUID id);

    int updateEstagiarioById(UUID id, Estagiario estagiario);
}
