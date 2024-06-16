package info.moraes.at_desenvolvimentoservicosspringboot.config;

import info.moraes.at_desenvolvimentoservicosspringboot.model.Departamento;
import info.moraes.at_desenvolvimentoservicosspringboot.model.Funcionario;
import info.moraes.at_desenvolvimentoservicosspringboot.repository.DepartamentoRepository;
import info.moraes.at_desenvolvimentoservicosspringboot.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Inicializar departamentos
        Departamento depto1 = new Departamento("Departamento A");
        Departamento depto2 = new Departamento("Departamento B");
        Departamento depto3 = new Departamento("Departamento C");

        departamentoRepository.saveAll(Arrays.asList(depto1, depto2, depto3));

        // Inicializar funcionários
        Funcionario func1 = new Funcionario("Funcionario 1", "Endereco 1", "Telefone 1", "email1@example.com", LocalDate.of(1990, 1, 1), depto1);
        Funcionario func2 = new Funcionario("Funcionario 2", "Endereco 2", "Telefone 2", "email2@example.com", LocalDate.of(1991, 2, 2), depto2);
        Funcionario func3 = new Funcionario("Funcionario 3", "Endereco 3", "Telefone 3", "email3@example.com", LocalDate.of(1992, 3, 3), depto3);

        funcionarioRepository.saveAll(Arrays.asList(func1, func2, func3));
    }
}
