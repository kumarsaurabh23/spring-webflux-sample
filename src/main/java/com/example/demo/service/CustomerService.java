package com.example.demo.service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getAllCustomers() {
        Instant start = Instant.now();
        List<Customer> customers = customerDao.getCustomers();
        Instant end = Instant.now();
        log.info("total time elapsed : {} milliseconds", Duration.between(start, end).toMillis());
        return customers;
    }

    public Flux<Customer> getAllCustomerStream() {
        Instant start = Instant.now();
        Flux<Customer> customers = customerDao.getCustomerStream();
        Instant end = Instant.now();
        log.info("total time elapsed : {} milliseconds", Duration.between(start, end).toMillis());
        return customers;
    }
}
