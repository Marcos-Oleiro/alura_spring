package med.voll.api.records.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Especialidade;

public record DadosAgendamentoConsulta(

    Long idMedico,

    @NotNull
    Long idPaciente,

    @NotNull
    @Future
    // @JsonFormat(pattern = "dd/MM/yyyy HH:mm")  -> para usar data em formatos diferentes, como "data": "10/12/2023 15:48"
    LocalDateTime data,

    Especialidade especialidade
) {}
