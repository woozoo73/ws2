package ws2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ws2.model.Email;

public interface EmailRepository extends JpaRepository<Email, String> {

}
