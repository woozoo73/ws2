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

}
