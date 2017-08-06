package com.lionsshareweb.customer.repository;

import com.lionsshareweb.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_SQL = "INSERT INTO customer (firstName, lastName, phone, email) VALUES(?,?,?,?)";
    @Override
    public void add(Customer customer){
        jdbcTemplate.update(INSERT_SQL, customer.getFirstName(),customer.getLastName(),customer.getPhone(), customer.getEmail());
    }

    private final String SELECT_BY_ID_SQL = "SELECT * FROM customer WHERE id=?";
    @Override
    public Customer getCustomerById(int id){
        return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new CustomerMapper(), id);
    }

    private final String SELECT_SQL = "SELECT * FROM customer";
    @Override
    public List<Customer> getCustomers() {
        return jdbcTemplate.query(SELECT_SQL, new CustomerMapper());
    }

    private final String UPDATE_SQL = "UPDATE customer SET firstName=?, lastName=?, phone=?, email=? where id=?";
    @Override
    public void update(Customer customer) {
        jdbcTemplate.update(UPDATE_SQL, customer.getFirstName(), customer.getLastName(), customer.getPhone(), customer.getEmail(), customer.getId());
    }

    private final String DELETE_SQL = "DELETE FROM customer WHERE id=?";
    @Override
    public void delete(Customer customer) {
        jdbcTemplate.update(DELETE_SQL, Integer.valueOf(customer.getId()));
    }

    private static class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setLastName(rs.getString("lastName"));
            customer.setPhone(rs.getString("phone"));
            return customer;
        }
    }
}
