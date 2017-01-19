package ws2.service;

import java.util.List;

import ws2.model.Customer;
import ws2.model.Email;

public interface CustomerService {

	List<Customer> list();

	Long create(Customer customer);

	Customer read(Long id);

	void update(Customer customer);

	void delete(Long id);

	void create(Long id, Email email);

}
