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
import med.voll.api.model.Medico;
import med.voll.api.records.medicos.DadosAtualizacaoMedico;
import med.voll.api.records.medicos.DadosCadastroMedico;
import med.voll.api.records.medicos.DadosDetalhamentoMedico;
import med.voll.api.records.medicos.DadosListagemMedico;
import med.voll.api.reps.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicosController {

    @Autowired
    MedicoRepository rep;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {

        Medico medicoNovo = rep.save(new Medico(dados));
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medicoNovo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {

        var medico = rep.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<?>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

        return ResponseEntity.ok(rep.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

        var medico = rep.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        DadosDetalhamentoMedico dadosDetalhamentoMedico = new DadosDetalhamentoMedico(medico);

        return ResponseEntity.ok(dadosDetalhamentoMedico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        var medico = rep.getReferenceById(id);

        medico.excluir();

        return ResponseEntity.noContent().build();
    }
}
