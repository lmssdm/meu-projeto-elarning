package elarning.ms_auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import elarning.ms_auth.entity.LogAcesso;

public interface LogAcessoRepository extends MongoRepository<LogAcesso, String> {
}