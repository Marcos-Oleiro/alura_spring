package med.voll.api.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.handler.exceptions.ValidacaoException;
import med.voll.api.model.Consulta;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;
import med.voll.api.records.consulta.DadosAgendamentoConsulta;
import med.voll.api.reps.ConsultaRepository;
import med.voll.api.reps.MedicoRepository;
import med.voll.api.reps.PacienteRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRep;

    @Autowired
    private PacienteRepository pracienteRep;

    @Autowired
    private MedicoRepository medicoRep;

    public void agendar(DadosAgendamentoConsulta dados) {

        if (!pracienteRep.existsById(dados.idPaciente()))
            throw new ValidacaoException("Paciente não encontrado");

        if (!medicoRep.existsById(dados.idMedico()) && Objects.nonNull(dados.idMedico()))
            throw new ValidacaoException("Médico não encontrado");

        Paciente paciente = pracienteRep.findById(dados.idPaciente()).get();

        // Medico medico = medicoRep.findById(dados.idMedico()).get();
        Medico medico = escolherMédico(dados);

        Consulta consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRep.save(consulta);
    }

    private Medico escolherMédico(DadosAgendamentoConsulta dados) {

        if (Objects.nonNull(dados.idMedico())) {
            return medicoRep.getReferenceById(dados.idMedico());
        }

        if (Objects.isNull(dados.especialidade())) {
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não é escolhido");
        }

        return medicoRep.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
