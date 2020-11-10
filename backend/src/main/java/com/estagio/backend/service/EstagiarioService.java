package com.estagio.backend.service;

import com.estagio.backend.dao.EstagiarioDao;
import com.estagio.backend.model.Estagiario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EstagiarioService {
    private final EstagiarioDao estagiarioDao;

    @Autowired
    public EstagiarioService(@Qualifier("fakeDao") EstagiarioDao estagiarioDao) {
        this.estagiarioDao = estagiarioDao;
    }

    public int addEstagiario(Estagiario estagiario){
        return estagiarioDao.insertEstagiario(estagiario);
    }

    public List<Estagiario> getAllPeople(){
        return estagiarioDao.selectAllPeople();
    }

    public Optional<Estagiario> getEstagiarioById(UUID id){
        return estagiarioDao.selectEstagiarioById(id);

    }

    public int deleteEstagiario(UUID id) {
        return estagiarioDao.deleteEstagiarioById(id);
    }

    public int updateEstagiario(UUID id, Estagiario newEstagiario){
        return estagiarioDao.updateEstagiarioById(id, newEstagiario);

    }
}
