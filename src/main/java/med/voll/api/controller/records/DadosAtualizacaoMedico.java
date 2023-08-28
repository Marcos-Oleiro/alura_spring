package med.voll.api.controller.records;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(

    @NotNull
    Long id,
    
    String nome,
    String telefone,
    DadosEndereco endereco
) 
{}
