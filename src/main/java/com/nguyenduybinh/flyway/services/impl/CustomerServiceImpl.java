package com.nguyenduybinh.flyway.services.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.nguyenduybinh.flyway.entities.Contact;
import com.nguyenduybinh.flyway.entities.Customer;
import com.nguyenduybinh.flyway.entities.Sex;
import com.nguyenduybinh.flyway.repositories.ContactRepository;
import com.nguyenduybinh.flyway.repositories.CustomerRepository;
import com.nguyenduybinh.flyway.requests.CustomerRequest;
import com.nguyenduybinh.flyway.response.CustomerResponse;
import com.nguyenduybinh.flyway.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}
	
	@Override
	public List<Customer> findCustomer(String name) {
		return customerRepository.findByName(name);
	}

	@Override
	public List<Contact> getCustomerContactsByCustomerId(Long customerId) {
		return customerRepository.findById(customerId).get().getContacts();
	}

	@Override
	public List<Contact> patchContacts(Long customerId, CustomerRequest customerRequest) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(customer.isPresent()) {
			Customer updatedCustomer = customer.get();
			List<Contact> contacts = updatedCustomer.getContacts();
			for(Contact contact : contacts) {
				for(Map<String, Object> map : customerRequest.getContacts()) {
					Long val = ((Number) map.get("id")).longValue();
					if(val == contact.getId()) {
						map.forEach((key, value) -> {
							if(key.equals("id")) {
								value = ((Number) value).longValue();
							}
							Field field = ReflectionUtils.findField(Contact.class, key);
							field.setAccessible(true);
							ReflectionUtils.setField(field, contact, value);
						});
						contactRepository.saveAndFlush(contact);
					}
				}
			}
			return customerRepository.findById(customerId).get().getContacts();
		}
		return null;
	}

	@Override
	public CustomerResponse getCustomer(Long customerId) {
		Customer customer = customerRepository.getById(customerId);
		CustomerResponse customerResponse = CustomerResponse.from(customer);
		customerResponse.setContacts(customer.getContacts());
		customerResponse.setSex(Sex.from(customer));
		return customerResponse;
	}

	@Override
	public CustomerResponse patchCustomer(Long customerId, CustomerRequest customerRequest) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		if(customer.isPresent()) {
			// update contact objects
			Customer updatedCustomer = customer.get();
			List<Contact> contacts = updatedCustomer.getContacts();
			for(Contact contact : contacts) {
				for(Map<String, Object> map : customerRequest.getContacts()) {
					Long val = ((Number) map.get("id")).longValue();
					if(val == contact.getId()) {
						map.forEach((key, value) -> {
							if(key.equals("id")) {
								value = ((Number) value).longValue();
							}
							Field field = ReflectionUtils.findField(Contact.class, key);
							field.setAccessible(true);
							ReflectionUtils.setField(field, contact, value);
						});
						contactRepository.saveAndFlush(contact);
					}
				}
			}
			// update sex object
			customerRequest.getSex().forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Customer.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, updatedCustomer, value);
			});
			customerRepository.saveAndFlush(updatedCustomer);
		}
		
		// set response customer model
		CustomerResponse customerResponse = getCustomer(customerId);
		return customerResponse;
	}


}
