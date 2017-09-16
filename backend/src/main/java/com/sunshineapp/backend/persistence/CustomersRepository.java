package com.sunshineapp.backend.persistence;


import com.sunshineapp.backend.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * todo: description.
 */

public interface CustomersRepository extends MongoRepository<Customer, String> {
}
