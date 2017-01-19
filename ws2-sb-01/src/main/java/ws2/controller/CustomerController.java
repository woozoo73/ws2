package ws2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ws2.model.Customer;
import ws2.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String list(Model model) {
		List<Customer> customerList = customerService.list();
		model.addAttribute("customerList", customerList);

		return "customer/list";
	}

	@RequestMapping(value = "/customer/new", method = RequestMethod.GET)
	public String form(@ModelAttribute Customer customer) {
		return "customer/new";
	}

	@RequestMapping(value = "/customer/new", method = RequestMethod.POST)
	public String create(@ModelAttribute Customer customer) {
		customerService.create(customer);

		return "redirect:/customer";
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		Customer customer = customerService.read(id);
		model.addAttribute("customer", customer);

		return "customer/view";
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable Long id, @ModelAttribute Customer customer) {
		customerService.update(customer);

		return "redirect:/customer";
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		customerService.delete(id);

		return "redirect:/customer";
	}

}
