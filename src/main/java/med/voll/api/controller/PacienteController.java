package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.controller.model.Paciente;
import med.voll.api.controller.records.DadosCadastroPacientes;
import med.voll.api.reps.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository rep;

    @PostMapping
    @Transactional
    public ResponseEntity<Paciente> cadastraPaciente (@RequestBody DadosCadastroPacientes dados){

        return ResponseEntity.ok(rep.save(new Paciente(dados)));
    }
    
}
