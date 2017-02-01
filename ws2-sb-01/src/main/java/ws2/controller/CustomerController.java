package ws2.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ws2.model.Customer;
import ws2.model.Customer.Type;
import ws2.model.Email;
import ws2.model.User;
import ws2.service.CustomerService;

@Controller
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String list(Model model) {
		List<Customer> customerList = customerService.list();
		model.addAttribute("customerList", customerList);

		return "customer/list";
	}

	@RequestMapping(value = "/customer/new", method = RequestMethod.GET)
	public String form(@ModelAttribute Customer customer, Model model) {
		List<Type> typeList = Arrays.asList(Customer.Type.values());
		model.addAttribute("typeList", typeList);

		return "customer/new";
	}

	@RequestMapping(value = "/customer/new", method = RequestMethod.POST)
	public String create(@Valid Customer customer, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Principal principal) {
		if (bindingResult.hasErrors()) {
			log.debug("bindingResult={}", bindingResult);

			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "customer", bindingResult);
			redirectAttributes.addFlashAttribute("customer", customer);

			return "redirect:customer/new";
		}

		User creator = new User();
		creator.setId(principal.getName());
		customer.setCreator(creator);

		customerService.create(customer);

		return "redirect:/customer";
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		if (!model.containsAttribute("customer")) {
			Customer customer = customerService.read(id);
			model.addAttribute("customer", customer);
		}

		List<Type> typeList = Arrays.asList(Customer.Type.values());
		model.addAttribute("typeList", typeList);

		return "customer/view";
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable Long id, @Valid Customer customer, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Principal principal) {
		if (bindingResult.hasErrors()) {
			log.debug("bindingResult={}", bindingResult);

			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "customer", bindingResult);
			redirectAttributes.addFlashAttribute("customer", customer);

			return "redirect:/customer/{id}";
		}

		User creator = new User();
		creator.setId(principal.getName());
		customer.setCreator(creator);

		// UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// log.debug("userDetails={}", userDetails);

		customerService.update(customer);

		return "redirect:/customer";
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		customerService.delete(id);

		return "redirect:/customer";
	}

	@RequestMapping(value = "/customer/{id}/email", method = RequestMethod.POST)
	public String createEmail(@PathVariable Long id, @ModelAttribute Email email) {
		customerService.createEmail(id, email);

		return "redirect:/customer/" + id;
	}

	@RequestMapping(value = "/customer/{id}/email/{address}", method = RequestMethod.DELETE)
	public String deleteEmail(@PathVariable Long id, @PathVariable String address, @ModelAttribute Email email) {
		customerService.deleteEmail(id, address);

		return "redirect:/customer/" + id;
	}

}
