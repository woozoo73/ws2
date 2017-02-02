package ws2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ws2.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, String> {

	Email findByAddress(String address);

}
