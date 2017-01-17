package ws2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String formNew(@ModelAttribute Customer customer) {
		return "customer/new";
	}

	@RequestMapping(value = "/customer/new", method = RequestMethod.POST)
	public String submitNew(@ModelAttribute Customer customer) {
		customerService.create(customer);

		return "redirect:/customer";
	}

}
