package info.moraes.at_desenvolvimentoservicosspringboot.repository;

import info.moraes.at_desenvolvimentoservicosspringboot.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {


}
