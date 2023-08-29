package med.voll.api.controller.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPacientes(

    @NotBlank
    String nome,

    @NotBlank @Email
    String email,

    @NotBlank
    String telefone,

    @NotBlank @Pattern(regexp = "\\d{11}")
    String cpf,

    @NotNull @Valid
    DadosEndereco endereco
) 
{}
