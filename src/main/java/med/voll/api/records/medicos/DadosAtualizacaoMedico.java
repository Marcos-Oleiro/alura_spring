package med.voll.api.records.medicos;

import jakarta.validation.constraints.NotNull;
import med.voll.api.records.DadosEndereco;

public record DadosAtualizacaoMedico(

    @NotNull
    Long id,
    
    String nome,
    String telefone,
    DadosEndereco endereco
) 
{}
