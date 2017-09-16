package com.sunshineapp.backend.controllers;

import com.sunshineapp.backend.annotations.Backend;
import com.sunshineapp.backend.config.BackendApi;
import com.sunshineapp.backend.models.dto.CustomerDto;
import com.sunshineapp.backend.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class CustomerController extends AbstractController {

    private final CustomerService customerService;

    @Inject
    public CustomerController(@Backend CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.POST, value = BackendApi.CUSTOMERS_API)
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto) {
        final CustomerDto result = customerService.create(customerDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = BackendApi.CUSTOMER_API)
    public CustomerDto get(@PathVariable String customerKey) {
        return customerService.get(customerKey);
    }

    @RequestMapping(method = RequestMethod.PUT, value = BackendApi.CUSTOMER_API)
    public CustomerDto update(@PathVariable String customerKey, @RequestBody CustomerDto customerDto) {
        customerDto.setCustomerKey(customerKey);
        return customerService.update(customerDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = BackendApi.CUSTOMER_API)
    public CustomerDto delete(@PathVariable String customerKey) {
        return customerService.delete(customerKey);
    }

}
