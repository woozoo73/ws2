package ws2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ws2.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
