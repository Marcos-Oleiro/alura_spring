package med.voll.api.controller.records;

import med.voll.api.controller.model.Especialidade;
import med.voll.api.controller.model.Medico;

public record DadosListagemMedico(

    Long id,
    String nome,
    String email,
    String crm,
    Especialidade especialidade
)
{
    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
