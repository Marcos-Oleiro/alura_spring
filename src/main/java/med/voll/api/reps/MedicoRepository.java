package med.voll.api.reps;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.controller.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
