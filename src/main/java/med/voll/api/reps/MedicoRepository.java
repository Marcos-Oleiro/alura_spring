package med.voll.api.reps;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m from medico m 
                where m.ativo = true and m.especialidade = :especialidade
                and m.id not in(
                    select c.medico.id from consulta c 
                        where c.data = :data
                )
                order by rand() limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
