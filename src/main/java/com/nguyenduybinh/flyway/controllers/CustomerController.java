package com.nguyenduybinh.flyway.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenduybinh.flyway.entities.Contact;
import com.nguyenduybinh.flyway.entities.Customer;
import com.nguyenduybinh.flyway.requests.CustomerRequest;
import com.nguyenduybinh.flyway.response.CustomerResponse;
import com.nguyenduybinh.flyway.services.impl.CustomerServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("customerId") Long customerId){
		CustomerResponse customerResponse = customerServiceImpl.getCustomer(customerId);
		return new ResponseEntity<>(customerResponse, HttpStatus.OK);
	}
	
	@GetMapping("/customers/contacts")
	public ResponseEntity<Object> getCustomerContacts(){
		List<Customer> customers = customerServiceImpl.getAllCustomer();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{customerId}/contacts")
	public ResponseEntity<Object> getCustomerContacts(@PathVariable("customerId") Long customerId){
		List<Contact> contacts = customerServiceImpl.getCustomerContactsByCustomerId(customerId);
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/{customerId}/contacts")
	public ResponseEntity<Object> patchContacts(@PathVariable("customerId") Long customerId, @RequestBody CustomerRequest customerRequest){
		List<Contact> contacts = customerServiceImpl.patchContacts(customerId, customerRequest);
		return new ResponseEntity<Object>(contacts, HttpStatus.OK);
	}
	
	@PatchMapping("/{customerId}")
	public ResponseEntity<CustomerResponse> patchCustomer(@PathVariable("customerId") Long customerId, @RequestBody CustomerRequest customerRequest){
		CustomerResponse customerResponse = customerServiceImpl.patchCustomer(customerId, customerRequest);
		return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.OK);
	}
}
