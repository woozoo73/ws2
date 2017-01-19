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

[Common application properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

[Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.extensions.querydsl)

[Restful API Design] (http://www.slideshare.net/apigee/restful-api-design-second-edition?qid=96e0890f-e162-4625-9861-b1c30fc034ba)

