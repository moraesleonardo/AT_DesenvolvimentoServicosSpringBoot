package info.moraes.at_desenvolvimentoservicosspringboot.service;

import info.moraes.at_desenvolvimentoservicosspringboot.model.Funcionario;
import info.moraes.at_desenvolvimentoservicosspringboot.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> getFuncionarioById(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario createFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> updateFuncionario(Long id, Funcionario funcionarioDetails) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if (funcionario.isPresent()) {
            Funcionario updatedFuncionario = funcionario.get();
            updatedFuncionario.setNome(funcionarioDetails.getNome());
            updatedFuncionario.setEndereco(funcionarioDetails.getEndereco());
            updatedFuncionario.setTelefone(funcionarioDetails.getTelefone());
            updatedFuncionario.setEmail(funcionarioDetails.getEmail());
            updatedFuncionario.setDataNascimento(funcionarioDetails.getDataNascimento());
            updatedFuncionario.setDepartamento(funcionarioDetails.getDepartamento());
            return Optional.of(funcionarioRepository.save(updatedFuncionario));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteFuncionario(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
