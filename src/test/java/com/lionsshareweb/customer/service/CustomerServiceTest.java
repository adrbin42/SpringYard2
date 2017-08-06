package com.lionsshareweb.customer.service;

import com.lionsshareweb.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    CustomerService customerService;

    @Test
    public void testAddGet(){

        //Tests to create and add a customer and to see if we can retrieve both with getAll and getById
        System.out.print("Adding customer to database...");
        Customer expected = createCustomer();
        customerService.add(expected);
        System.out.println("Done!");

        //Test getAllCustomers()
        System.out.print("Getting all customers from the database...");
        List<Customer> customers = customerService.getCustomers();
        System.out.println("Done!");
        System.out.print("Getting expected customer...");
        Customer receivedFromAll = findFirstInList(customers,expected);
        System.out.println("Done!");
        System.out.print("Testing getAllCustomers()....");
        Assert.assertNotNull(receivedFromAll);
        Assert.assertEquals(receivedFromAll.getFirstName(),expected.getFirstName());
        Assert.assertEquals(receivedFromAll.getLastName(),expected.getLastName());
        Assert.assertEquals(receivedFromAll.getPhone(),expected.getPhone());
        System.out.println("Passed!");

        //Test getCustomerById()
        System.out.println("Running ID test using the customer:");
        System.out.println("\t" + receivedFromAll);
        System.out.print("Getting customer by ID...");
        Customer receivedById = customerService.getCustomerById(receivedFromAll.getId());
        System.out.println("\tFound the following customer:");
        System.out.println("\t\t" + receivedById);
        System.out.println("Done!");
        System.out.print("Testing getCustomerById()....");
        Assert.assertNotNull(receivedById);
        Assert.assertEquals(receivedById.getFirstName(),receivedFromAll.getFirstName());
        Assert.assertEquals(receivedById.getLastName(),receivedFromAll.getLastName());
        Assert.assertEquals(receivedById.getPhone(),receivedFromAll.getPhone());
        System.out.println("Passed!");

    }

    @Test
    public void testUpdate(){
        Customer testCustomer = createCustomer();
        customerService.add(testCustomer);

        List<Customer> customers = customerService.getCustomers();
        Customer updatedCustomer = findFirstInList(customers,testCustomer);
        updatedCustomer.setFirstName("NewFirstName");
        updatedCustomer.setLastName("NewLastName");
        updatedCustomer.setPhone("NewPhone");
        customerService.update(updatedCustomer);

        Customer expected = customerService.getCustomerById(updatedCustomer.getId());
        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getFirstName(),updatedCustomer.getFirstName());
        Assert.assertEquals(expected.getLastName(),updatedCustomer.getLastName());
        Assert.assertEquals(expected.getPhone(),updatedCustomer.getPhone());

    }

    @Test
    public void testDelete(){
        Customer testCustomer = createCustomer();
        customerService.add(testCustomer);

        List<Customer> customers = customerService.getCustomers();
        Customer toBeDeleted = findFirstInList(customers,testCustomer);
        Assert.assertNotNull(toBeDeleted);

        customerService.delete(toBeDeleted);

        customers = customerService.getCustomers();
        Customer expected = findFirstInList(customers,toBeDeleted);
        Assert.assertNull(expected);
    }

    private Customer createCustomer(){
        Customer testCustomer = new Customer();
        String firstName = Long.toString(System.currentTimeMillis());
        delay(2);
        String lastName = Long.toString(System.currentTimeMillis());
        delay(2);
        String phone = Long.toString(System.currentTimeMillis());
        delay(2 );

        testCustomer.setFirstName(firstName);
        testCustomer.setLastName(lastName);
        testCustomer.setPhone(phone);

        return testCustomer;
    }

    private void delay(long seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private Customer findFirstInList(List<Customer> customers, Customer instance){
        String firstName = instance.getFirstName();
        String lastName = instance.getLastName();
        String phone = instance.getPhone();

        for(Customer customer : customers){
            if(customer.getFirstName().equals(firstName) &&
               customer.getLastName().equals(lastName) &&
               customer.getPhone().equals(phone)){ return customer; }
        }
        return null;
    }
}
