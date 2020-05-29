package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        Address a = (Address) applicationContext.getBean("aAddress");
        System.out.println(a);
        Company c = applicationContext.getBean(Company.class);
        c.printAddress();
    }

}