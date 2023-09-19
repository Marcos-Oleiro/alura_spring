package med.voll.api.records.pacientes;

import jakarta.validation.constraints.NotNull;
import med.voll.api.records.DadosEndereco;

public record DadosAtualizacaoPaciente (

    @NotNull
    Long id,
    
    String nome, 
    String telefone,
    DadosEndereco endereco
)
{}
