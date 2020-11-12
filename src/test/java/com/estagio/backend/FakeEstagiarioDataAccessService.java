package com.estagio.backend;

import com.estagio.backend.dao.EstagiarioDao;
import com.estagio.backend.model.Estagiario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class FakeEstagiarioDataAccessService implements EstagiarioDao {
    private static final List<Estagiario> DB = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public FakeEstagiarioDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertEstagiario(UUID id, Estagiario estagiario) {
        DB.add(new Estagiario(id, estagiario.getNome(), estagiario.getEmail(), estagiario.getTelefone(), estagiario.getFoto(), estagiario.getComprovanteMatricula(), estagiario.getInteresses(), estagiario.getDominios()));
        return 1;
    }

    @Override
    public List<Estagiario> selectAllPeople() {


        return DB;
    }

    @Override
    public Optional<Estagiario> selectEstagiarioById(UUID id) {
        return DB.stream().filter(estagiario -> estagiario.getId().equals(id)).
                findFirst();
    }

    @Override
    public int deleteEstagiarioById(UUID id) {
        Optional<Estagiario> estagiarioMaybe = selectEstagiarioById(id);
        if(estagiarioMaybe.isEmpty()){
            return 0;
        }

        DB.remove(estagiarioMaybe.get());
        return 1;
    }

    @Override
    public int updateEstagiarioById(UUID id, Estagiario update) {

        return selectEstagiarioById(id)
                .map(estagiario ->{
                    int indexOfEstagiarioToUpdate = DB.indexOf(estagiario);
                    if(indexOfEstagiarioToUpdate >= 0) {
                        DB.set(indexOfEstagiarioToUpdate, new Estagiario(id, update.getNome(),
                                update.getEmail(),
                                update.getTelefone(),
                                update.getFoto(),
                                update.getComprovanteMatricula(),
                                update.getInteresses(),
                                update.getDominios()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
