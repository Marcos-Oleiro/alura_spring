package med.voll.api.controller;

import java.net.URI;

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
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.model.Paciente;
import med.voll.api.records.pacientes.DadosAtualizacaoPaciente;
import med.voll.api.records.pacientes.DadosCadastroPacientes;
import med.voll.api.records.pacientes.DadosDetalhamentoPaciente;
import med.voll.api.records.pacientes.DadosListagemPacientes;
import med.voll.api.reps.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository rep;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroPacientes dados, UriComponentsBuilder uriBuilder) {

        Paciente pacienteNovo = rep.save(new Paciente(dados));
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacienteNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(pacienteNovo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        
        var paciente = rep.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<?>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

        return ResponseEntity.ok(rep.findAllByAtivoTrue(paginacao).map(DadosListagemPacientes::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody DadosAtualizacaoPaciente dados) {

        var paciente = rep.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        DadosDetalhamentoPaciente dadosDetalhamentoPaciente = new DadosDetalhamentoPaciente(paciente);

        return ResponseEntity.ok(dadosDetalhamentoPaciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        var paciente = rep.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }

}
