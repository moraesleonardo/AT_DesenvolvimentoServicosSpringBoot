package info.moraes.at_desenvolvimentoservicosspringboot.repository;


import info.moraes.at_desenvolvimentoservicosspringboot.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}


