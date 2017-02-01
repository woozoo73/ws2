# ws2
work &amp; study 2nd

## 2017.01.20

### JPA

Customer : update, delete

@OneToMany : Customer / Email

#####Customer.java

    @Entity
    public class Customer {

        ...
        
        @OneToMany
        private List<Email> emailList;

        ...
        
    }

#####Email.java

    @Entity
    public class Email {
        
        @Id
        private String address;
        
        private String type;

        ...
        
    }

### MongoDB

    lab@lab:~$ mongo
    MongoDB shell version: 2.6.1
    connecting to: test
    > use ws2;
    switched to db ws2
    > db.myEmail.insert({address:'woozoo73@bluedigm.com', type:'office'});
    WriteResult({ "nInserted" : 1 })
    > db.myEmail.find({address:'woozoo73@bluedigm.com'});
    { "_id" : ObjectId("588162c7400bf8ef4a0d6cf8"), "address" : "woozoo73@bluedigm.com", "type" : "office" }
    > db.myEmail.find({address:'woozoo73@bluedigm.com'}).pretty();
    {
        "_id" : ObjectId("588162c7400bf8ef4a0d6cf8"),
        "address" : "woozoo73@bluedigm.com",
        "type" : "office"
    }
    > db.myEmail.findOne({address:'woozoo73@bluedigm.com'});
    {
        "_id" : ObjectId("588162c7400bf8ef4a0d6cf8"),
        "address" : "woozoo73@bluedigm.com",
        "type" : "office"
    }
    > db.myEmail.findOne("588162c7400bf8ef4a0d6cf8");
    null
    > db.myEmail.findOne({_id:"588162c7400bf8ef4a0d6cf8"});
    null
    > db.myEmail.findOne({_id:ObjectId("588162c7400bf8ef4a0d6cf8")});
    {
        "_id" : ObjectId("588162c7400bf8ef4a0d6cf8"),
        "address" : "woozoo73@bluedigm.com",
        "type" : "office"
    }


#####application.properties
    logging.level.org.springframework.data.mongodb.core.MongoTemplate=DEBUG

[Common application properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

[Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.extensions.querydsl)

[Restful API Design] (http://www.slideshare.net/apigee/restful-api-design-second-edition?qid=96e0890f-e162-4625-9861-b1c30fc034ba)

## 2017.01.25

[UserService < UserDetails service]

    package ws2.service;

    import java.util.ArrayList;
    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    import ws2.model.User;
    import ws2.repository.UserRepository;

    @Service
    public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String paramString) throws UsernameNotFoundException {
            User user = userRepository.findOne(paramString);

            if (user == null) {
                throw new UsernameNotFoundException("Not found user");
            }

            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
            authorities.add(authority);

            return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), authorities);
        }

        @Override
        public void create(User user) {
            userRepository.save(user);
        }

    }

[Customize security]

http://stackoverflow.com/questions/31524426/securityconfig-2-success-url-for-different-roles

## 2017.02.02


[Customer] (https://github.com/woozoo73/ws2/blob/master/ws2-sb-01/src/main/java/ws2/model/Customer.java)

    @Entity
    public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @NotNull
        @Size(min = 2, max = 30)
        private String firstName;

        @NotNull
        @Size(min = 2, max = 30)
        private String lastName;

        @NotNull
        @Enumerated(EnumType.STRING)
        private Type type;

        @OneToMany
        private List<Email> emailList;

        @ManyToOne
        private User creator;

        ...
        
        public enum Type {
            Gold, Silver, Bronze,
        };

    }

[CustomerController] (https://github.com/woozoo73/ws2/blob/master/ws2-sb-01/src/main/java/ws2/controller/CustomerController.java)

    @Controller
    public class CustomerController {

        private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

        ...

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

            customerService.update(customer);

            return "redirect:/customer";
        }

        ...

    }

[new.jsp] (https://github.com/woozoo73/ws2/blob/master/ws2-sb-01/src/main/webapp/WEB-INF/jsp/customer/new.jsp)

    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

        <form:form commandName="customer">
        <table class="table">
            <thead>
                <tr>
                    <th>key</th>
                    <th>value</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>first name</th>
                    <td>
                        <form:input path="firstName" />
                        <form:errors path="firstName" />
                    </td>
                </tr>
                <tr>
                    <th>last name</th>
                    <td>
                        <form:input path="lastName" />
                        <form:errors path="lastName" />
                    </td>
                </tr>
                <tr>
                    <th>type</th>
                    <td>
                        <form:select path="type">
                            <option />
                            <form:options />
                        </form:select>
                        <form:errors path="type" />
                    </td>				
                </tr>
            </tbody>
        </table>

        <input type="submit" class="btn btn-success" value="create" />
        </form:form>

[view.jsp] (https://github.com/woozoo73/ws2/blob/master/ws2-sb-01/src/main/webapp/WEB-INF/jsp/customer/view.jsp)

    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

        <form:form commandName="customer" id="customerForm">
        <input type="hidden" name="_method" value="" />
        <table class="table">
            <thead>
                <tr>
                    <th>key</th>
                    <th>value</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>first name</th>
                    <td>
                        <form:input path="firstName" />
                        <form:errors path="firstName" />
                    </td>
                </tr>
                <tr>
                    <th>last name</th>
                    <td>
                        <form:input path="lastName" />
                        <form:errors path="lastName" />
                    </td>
                </tr>
                <tr>
                    <th>type</th>
                    <td>
                        <form:select path="type">
                            <option />
                            <form:options />
                        </form:select>
                        <form:errors path="type" />
                    </td>				
                </tr>
            </tbody>
        </table>

        <a href="javascript:updateCustomer();" class="btn btn-primary">update</a>
        <a href="javascript:deleteCustomer();" class="btn btn-danger">delete</a>
        <a href="/customer" class="btn btn-info">cancel</a>
        </form:form>
