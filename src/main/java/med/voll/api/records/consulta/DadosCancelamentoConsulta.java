package med.voll.api.records.consulta;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.MotivoCancelamento;
import med.voll.api.util.EnumCustomDeserializer;

public record DadosCancelamentoConsulta(

    Long idConsulta,

    @NotNull
    @JsonDeserialize(using = EnumCustomDeserializer.class)
    MotivoCancelamento motivo
) {}
