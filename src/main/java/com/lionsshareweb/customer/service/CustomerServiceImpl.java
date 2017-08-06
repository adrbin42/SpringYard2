package com.lionsshareweb.customer.service;

import com.lionsshareweb.customer.model.Customer;
import com.lionsshareweb.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public void add(Customer customer){
        customerRepository.add(customer);
    }

    @Override
    public Customer getCustomerById(int id){
        return customerRepository.getCustomerById(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    @Transactional
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
}
