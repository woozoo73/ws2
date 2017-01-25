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


    package hello;

    import java.util.ArrayList;
    import java.util.List;

    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    @Service
    public class UserServiceImpl implements UserService {

        @Override
        public UserDetails loadUserByUsername(String paramString) throws UsernameNotFoundException {
            if ("user".equals(paramString)) {
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
                authorities.add(authority);

                return new User("user", "password", authorities);
            }

            throw new UsernameNotFoundException("Not found user");
        }

    }
