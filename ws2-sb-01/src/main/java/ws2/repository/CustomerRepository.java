package ws2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ws2.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
