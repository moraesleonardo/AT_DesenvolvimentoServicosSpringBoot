package info.moraes.at_desenvolvimentoservicosspringboot.repository;

import info.moraes.at_desenvolvimentoservicosspringboot.model.Funcionario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Test
    void testFindAll() {
        Funcionario func1 = new Funcionario("Funcionario 1", "Endereco 1", "Telefone 1", "email1@example.com", LocalDate.of(1990, 1, 1), null);
        Funcionario func2 = new Funcionario("Funcionario 2", "Endereco 2", "Telefone 2", "email2@example.com", LocalDate.of(1991, 2, 2), null);
        funcionarioRepository.save(func1);
        funcionarioRepository.save(func2);

        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        assertThat(funcionarios).hasSize(2).extracting(Funcionario::getNome).containsExactlyInAnyOrder("Funcionario 1", "Funcionario 2");
    }

    @Test
    void testSave() {
        Funcionario func = new Funcionario("Funcionario Teste", "Endereco Teste", "Telefone Teste", "emailteste@example.com", LocalDate.of(1990, 1, 1), null);
        Funcionario savedFunc = funcionarioRepository.save(func);
        assertThat(savedFunc).isNotNull();
        assertThat(savedFunc.getId()).isNotNull();
        assertThat(savedFunc.getNome()).isEqualTo("Funcionario Teste");
    }
}
