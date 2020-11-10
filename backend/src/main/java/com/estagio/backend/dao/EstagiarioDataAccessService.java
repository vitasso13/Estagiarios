package com.estagio.backend.dao;

import com.estagio.backend.dao.EstagiarioDao;
import com.estagio.backend.model.Estagiario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public abstract class EstagiarioDataAccessService implements EstagiarioDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EstagiarioDataAccessService(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertEstagiario(Estagiario estagiario) {
        String sql = "" +
                "INSERT INTO estagiario (" +
                " nome, " +
                "email, " +
                " telefone, " +
                " foto, " +
                "comprovanteMatricula, " +
                " interesses, " +
                " dominios) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
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
        return Optional.empty();
    }

    /*@Override
    public Optional<Estagiario> selectEstagiarioById(UUID id) {
        final String sql = "SELECT * FROM estagiario WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, (resultSet, i) -> {
            id = UUID.fromString(resultSet.getString("id"));
            String nome = resultSet.getString("nome");
            String email = resultSet.getString("email");
            String telefone = resultSet.getString("telefone");
            String foto = resultSet.getString("foto");
            String comprovanteMatricula = resultSet.getString("comprovanteMatricula");
            String interesses = resultSet.getString("interesses");
            String dominios = resultSet.getString("dominios");
            return new Estagiario(id, nome, email, telefone, foto, comprovanteMatricula, interesses, dominios);
        });


    }*/

    @Override
    public int deleteEstagiarioById(UUID id) {
        String sql ="DELETE FROM estagiario WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateEstagiarioById(UUID id, Estagiario estagiario) {
            String sql = "" +
                    "UPDATE estagiario" +
                    " SET nome = ?," +
                    " SET email = ?," +
                    " SET telefone = ?," +
                    " SET foto = ?," +
                    " SET comprovanteMatricula = ?," +
                    " SET interesses = ?," +
                    " SET dominios = ?," +
                    "WHERE id = ?";
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
