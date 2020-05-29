package spring;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class Company {
    
    Address address = null;

    ArrayList<String> employees = new ArrayList<>();

    public Company(Address a){
        this.address = a;
    }

    void printAddress() {
        System.out.println(this.address);
    }

}