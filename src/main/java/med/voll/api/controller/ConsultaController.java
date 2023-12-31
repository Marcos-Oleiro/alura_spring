package med.voll.api.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.model.Consulta;
import med.voll.api.records.consulta.DadosAgendamentoConsulta;
import med.voll.api.records.consulta.DadosCancelamentoConsulta;
import med.voll.api.records.consulta.DadosDetalhamentoConsulta;
import med.voll.api.service.ConsultaService;


@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;


    @PostMapping
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){

        Consulta consulta = service.agendar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){

        // Consulta consulta = service.agendar(dados);
        System.out.println(LocalDateTime.now());
        System.out.println("daods" + dados);

        // return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta));
        return ResponseEntity.ok(dados);
    }
}
