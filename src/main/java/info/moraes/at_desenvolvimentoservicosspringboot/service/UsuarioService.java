package info.moraes.at_desenvolvimentoservicosspringboot.service;


import info.moraes.at_desenvolvimentoservicosspringboot.model.Usuario;
import info.moraes.at_desenvolvimentoservicosspringboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(String id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> updateUsuario(String id, Usuario usuarioDetails) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario updatedUsuario = usuario.get();
            updatedUsuario.setNome(usuarioDetails.getNome());
            updatedUsuario.setSenha(usuarioDetails.getSenha());
            updatedUsuario.setPapel(usuarioDetails.getPapel());
            return Optional.of(usuarioRepository.save(updatedUsuario));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteUsuario(String id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
