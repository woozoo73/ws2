package ws2.service;

import java.util.List;

import ws2.model.Customer;

public interface CustomerService {

	List<Customer> list();

	Long create(Customer customer);

	Customer read(Long id);

	void update(Customer customer);

	void delete(Long id);

}
