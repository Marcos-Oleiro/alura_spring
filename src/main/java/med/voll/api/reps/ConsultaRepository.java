package med.voll.api.reps;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>  {
    
}
