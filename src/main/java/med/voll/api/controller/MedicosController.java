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
import med.voll.api.controller.model.Medico;
import med.voll.api.controller.records.DadosAtualizacaoMedico;
import med.voll.api.controller.records.DadosCadastroMedico;
import med.voll.api.controller.records.DadosListagemMedico;
import med.voll.api.reps.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicosController {

    @Autowired
    MedicoRepository rep;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCadastroMedico> insereMedico(@RequestBody @Valid DadosCadastroMedico dados) {

        rep.save(new Medico(dados));
        return ResponseEntity.ok(dados);
    }

    @GetMapping 
    public ResponseEntity<Page<DadosListagemMedico>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

        return ResponseEntity.ok(rep.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){

        var medico = rep.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar (@PathVariable Long id){

        var medico = rep.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.ok(null);
    }
}
