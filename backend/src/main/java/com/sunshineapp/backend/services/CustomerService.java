package com.sunshineapp.backend.services;

import com.sunshineapp.backend.annotations.Backend;
import com.sunshineapp.backend.models.dto.CustomerDto;
import com.sunshineapp.backend.persistence.CustomersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Backend
public class CustomerService {

    private static final String stubMessage = "stub";
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomersRepository repository;

    @Inject
    public CustomerService(CustomersRepository repository) {
        this.repository = repository;
    }

    public CustomerDto create(CustomerDto customerDto) {
        logger.debug("create customerDto={}", customerDto);

        // add security check
        // add auth check
        // add validation
        // add DB query

        return customerDto;
    }

    public CustomerDto get(String customerKey) {
        // add security check
        // add auth check
        // add DB query

//        List<Customer> all = repository.findAll();
//        Customer customer = all.iterator().next();

        return createStubCustomer(customerKey);
    }

    public CustomerDto update(CustomerDto customerDto) {
        logger.debug("update customerDto={}", customerDto);

        // Don't change some fields, e.g., password

        // add security check
        // add auth check
        // add validation
        // add DB query

        return customerDto;
    }

    public CustomerDto delete(String customerKey) {
        logger.debug("delete customerKey={}", customerKey);

        // Если юзер имеет хотя бы одно приложение - запретить удаление и сообщить причину.

        // add security check
        // add auth check
        // add DB query

        return createStubCustomer(customerKey);
    }

    private CustomerDto createStubCustomer(String customerKey) {
        final CustomerDto mockUser = new CustomerDto();

        mockUser.setCustomerKey(customerKey);
        mockUser.setLogin(stubMessage);
        mockUser.setEmail(stubMessage);

        return mockUser;
    }

}
