package ws2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ws2.model.Customer;
import ws2.model.Email;
import ws2.repository.CustomerRepository;
import ws2.repository.EmailRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmailRepository emailRepository;

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
		Customer saved = customerRepository.findOne(customer.getId());
		saved.setFirstName(customer.getFirstName());
		saved.setLastName(customer.getLastName());
		saved.setType(customer.getType());
		saved.setCreator(customer.getCreator());
	}

	@Override
	public void delete(Long id) {
		customerRepository.delete(id);
	}

	@Override
	public void createEmail(Long id, Email email) {
		Email saved = emailRepository.save(email);

		Customer customer = customerRepository.findOne(id);
		customer.getEmailList().add(saved);
	}

	@Override
	public void deleteEmail(Long id, String address) {
		Email email = emailRepository.findOne(address);
		Customer customer = customerRepository.findOne(id);
		customer.getEmailList().remove(email);

		emailRepository.delete(address);
	}

	@Override
    public boolean existEmailAddress(String address) {
		Email email = emailRepository.findByAddress(address);

		return email != null;
    }

}
