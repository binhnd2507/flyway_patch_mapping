package com.nguyenduybinh.flyway.response;

import java.util.List;

import com.nguyenduybinh.flyway.entities.Contact;
import com.nguyenduybinh.flyway.entities.Customer;
import com.nguyenduybinh.flyway.entities.Sex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

	private String name;
	
	private List<Contact> contacts;
	
	private Sex sex;
	
	public static CustomerResponse from(Customer customer) {
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.fillWith(customer);
		return customerResponse;
	}

	private void fillWith(Customer customer) {
		if(customer == null) {
			return;
		}
		this.name = customer.getName();
	}
}
