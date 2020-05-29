package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "spring")
public class Config {

    @Bean
    public Address aAddress(){
        return new Address("5 ducati apartments", "Ferrari Road");
    }

    @Bean
    public Address bAddress() {
        return new Address("5 tarantula Street", "Britaing, England, UK");
    }

    // @Bean
    // public Company aCompany(@Qualifier("aAddress") final Address address) {
    //     return new Company(address);
    // }

    @Bean
    public Company bCompany(@Qualifier("bAddress") Address a){
        return new Company(a);
    }

}