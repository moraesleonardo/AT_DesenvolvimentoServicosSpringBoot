package info.moraes.at_desenvolvimentoservicosspringboot.repository;

import info.moraes.at_desenvolvimentoservicosspringboot.model.Departamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DepartamentoRepositoryTest {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Test
    void testFindAll() {
        Departamento dept1 = new Departamento("Departamento A");
        Departamento dept2 = new Departamento("Departamento B");
        departamentoRepository.save(dept1);
        departamentoRepository.save(dept2);

        List<Departamento> departamentos = departamentoRepository.findAll();
        assertThat(departamentos).hasSize(2).extracting(Departamento::getNome).containsExactlyInAnyOrder("Departamento A", "Departamento B");
    }
}
