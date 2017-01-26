package ws2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ws2.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
