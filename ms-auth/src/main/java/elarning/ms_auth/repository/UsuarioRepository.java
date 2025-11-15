package elarning.ms_auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import elarning.ms_auth.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
    Optional<Usuario> findByEmail(String email);
}