package ws2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws2.model.Customer;
import ws2.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> list() {
		return customerRepository.findAll();
	}

	public Long create(Customer customer) {
		Customer saved = customerRepository.save(customer);

		return saved.getId();
	}

	@Override
	public Customer read(Long id) {
		Customer customer = customerRepository.findOne(id);

		return customer;
	}

	@Override
	public void update(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public void delete(Long id) {
		customerRepository.delete(id);
	}

}
