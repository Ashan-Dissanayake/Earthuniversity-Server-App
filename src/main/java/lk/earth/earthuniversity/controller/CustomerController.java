package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.CustomerDao;
import lk.earth.earthuniversity.dao.GenderDao;
import lk.earth.earthuniversity.entity.Customer;
import lk.earth.earthuniversity.entity.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerDao customerdao;

    @GetMapping(path ="/list",produces = "application/json")
    public List<Customer> get() {

        List<Customer> customers = this.customerdao.findAll();

        customers = customers.stream().map(
                customer -> { Customer c = new Customer();
                            c.setId(customer.getId());
                            c.setName(customer.getName());
                            return c; }
        ).collect(Collectors.toList());

        return customers;

    }

}


