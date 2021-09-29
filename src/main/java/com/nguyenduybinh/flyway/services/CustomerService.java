package com.nguyenduybinh.flyway.services;

import java.util.List;

import com.nguyenduybinh.flyway.entities.Contact;
import com.nguyenduybinh.flyway.entities.Customer;
import com.nguyenduybinh.flyway.requests.CustomerRequest;
import com.nguyenduybinh.flyway.response.CustomerResponse;

public interface CustomerService {
	
	List<Customer> getAllCustomer();

	List<Customer> findCustomer(String name);
	
	List<Contact> getCustomerContactsByCustomerId(Long customerId);
	
	List<Contact> patchContacts(Long customerId, CustomerRequest customerRequest);
	
	CustomerResponse getCustomer(Long customerId);
	
	CustomerResponse patchCustomer(Long customerId, CustomerRequest customerRequest);
}
