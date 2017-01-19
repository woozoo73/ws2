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

#####application.properties
    logging.level.org.springframework.data.mongodb.core.MongoTemplate=DEBUG

