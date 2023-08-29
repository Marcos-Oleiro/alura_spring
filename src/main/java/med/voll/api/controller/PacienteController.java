package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.model.Paciente;
import med.voll.api.records.DadosAtualizacaoPaciente;
import med.voll.api.records.DadosCadastroPacientes;
import med.voll.api.records.DadosListagemPacientes;
import med.voll.api.reps.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository rep;

    @PostMapping
    @Transactional
    public ResponseEntity<Paciente> cadastrar(@RequestBody @Valid DadosCadastroPacientes dados) {

        return ResponseEntity.ok(rep.save(new Paciente(dados)));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacientes>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

        return ResponseEntity.ok(rep.findAllByAtivoTrue(paginacao).map(DadosListagemPacientes::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosAtualizacaoPaciente> atualizar(@RequestBody DadosAtualizacaoPaciente dados){
        
        var paciente = rep.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){

        var paciente = rep.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.ok(null);
    }

}
