package med.voll.api.reps;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.controller.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente,Long>{
    
}
