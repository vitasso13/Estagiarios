package com.estagio.backend.dao;

import com.estagio.backend.dao.EstagiarioDao;
import com.estagio.backend.model.Estagiario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
@Repository("postgres")
public class EstagiarioDataAccessService implements EstagiarioDao {
    private static final List<Estagiario> DB = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public EstagiarioDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertEstagiario(UUID id, Estagiario estagiario) {
        String sql = "" +
                "INSERT INTO estagiario (" +
                " id,"+
                " nome, " +
                "email, " +
                " telefone, " +
                " foto, " +
                "comprovanteMatricula, " +
                " interesses, " +
                " dominios) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                estagiario.getNome(),
                estagiario.getEmail(),
                estagiario.getTelefone(),
                estagiario.getFoto(),
                estagiario.getComprovanteMatricula(),
                estagiario.getInteresses(),
                estagiario.getDominios());
    }

    @Override
    public List<Estagiario> selectAllPeople() {
        final String sql = "SELECT * FROM estagiario";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String nome = resultSet.getString("nome");
            String email = resultSet.getString("email");
            String telefone = resultSet.getString("telefone");
            String foto = resultSet.getString("foto");
            String comprovanteMatricula = resultSet.getString("comprovanteMatricula");
            String interesses = resultSet.getString("interesses");
            String dominios = resultSet.getString("dominios");
            return new Estagiario(id, nome, email, telefone, foto, comprovanteMatricula, interesses, dominios);
        });
    }

    @Override
    public Optional<Estagiario> selectEstagiarioById(UUID id) {
        return DB.stream().filter(estagiario -> estagiario.getId().equals(id)).
                findFirst();
    }

    @Override
    public int deleteEstagiarioById(UUID id) {
        String sql ="DELETE FROM estagiario WHERE id = ?";
        return jdbcTemplate.update(sql, id);

    }

    @Override
    public int updateEstagiarioById(UUID id, Estagiario estagiario) {
        String sql = "UPDATE estagiario" +
                " SET nome = ?," +
                " email = ?," +
                " telefone = ?," +
                " foto = ?," +
                " comprovanteMatricula = ?," +
                " interesses = ?," +
                " dominios = ?" +
                " WHERE id = ?";
        return jdbcTemplate.update(sql, estagiario.getNome(),
                estagiario.getEmail(),
                estagiario.getTelefone(),
                estagiario.getFoto(),
                estagiario.getComprovanteMatricula(),
                estagiario.getInteresses(),
                estagiario.getDominios(),
                id);
    }
}