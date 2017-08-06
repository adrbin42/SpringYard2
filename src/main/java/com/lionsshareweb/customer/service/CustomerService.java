package com.lionsshareweb.customer.service;

import com.lionsshareweb.customer.model.Customer;
import java.util.List;

public interface CustomerService {
    void add(Customer customer);
    Customer getCustomerById(int id);
    List<Customer> getCustomers();
    void update(Customer customer);
    void delete(Customer customer);
}
