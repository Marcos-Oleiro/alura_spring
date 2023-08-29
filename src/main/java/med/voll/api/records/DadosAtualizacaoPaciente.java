package med.voll.api.records;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente (

    @NotNull
    Long id,
    
    String nome, 
    String telefone,
    DadosEndereco endereco
)
{}
