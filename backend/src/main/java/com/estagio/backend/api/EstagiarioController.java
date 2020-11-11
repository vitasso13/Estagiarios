package com.estagio.backend.api;

import com.estagio.backend.model.Estagiario;
import com.estagio.backend.service.EstagiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/estagiario")
@RestController

@CrossOrigin(origins = { "*" })
public class EstagiarioController {
    private final EstagiarioService estagiarioService;

    @Autowired
    public EstagiarioController(EstagiarioService estagiarioService) {
        this.estagiarioService = estagiarioService;
    }

    @PostMapping
    public void addEstagiario(@Valid @NotNull @RequestBody Estagiario estagiario) {
        estagiarioService.addEstagiario(estagiario);
    }
    @GetMapping
    public List<Estagiario> getAllPeople(){
        return estagiarioService.getAllPeople();
    }
    @GetMapping(path = "{id}")
    public Estagiario getEstagiarioById(@PathVariable("id") UUID id){
        return estagiarioService.getEstagiarioById(id)
                .orElse(null);

    }
    @DeleteMapping(path = "{id}")
    public void deleteEstagiarioById(@PathVariable("id") UUID id){
        estagiarioService.deleteEstagiario(id);
    }
    @PutMapping(path = "{id}")
    public void updateEstagiario(@PathVariable("id") UUID id,@Valid @NotNull @RequestBody Estagiario estagiarioToUpdate){
        estagiarioService.updateEstagiario(id, estagiarioToUpdate);
    }
}
