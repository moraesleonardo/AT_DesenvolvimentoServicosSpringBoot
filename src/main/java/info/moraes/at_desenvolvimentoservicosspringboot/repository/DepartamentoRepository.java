package info.moraes.at_desenvolvimentoservicosspringboot.repository;

import info.moraes.at_desenvolvimentoservicosspringboot.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
