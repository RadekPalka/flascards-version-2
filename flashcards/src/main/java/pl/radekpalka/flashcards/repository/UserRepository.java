package pl.radekpalka.flashcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.radekpalka.flashcards.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}