package br.com.mundodev.dbbyenvironment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mundodev.dbbyenvironment.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	Optional<UserEntity> findByLogin(final String login);

}
