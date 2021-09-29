package com.nguyenduybinh.flyway.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenduybinh.flyway.entities.Contact;
import com.nguyenduybinh.flyway.services.impl.ContactServiceImpl;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactServiceImpl contactServiceImpl;
	
	@PatchMapping("/{id}")
	public ResponseEntity<Object> updateContact(
			@PathVariable("id") Long id, 
			@RequestBody Map<String, Object> fields
			){
		
		Contact contact = contactServiceImpl.patch(id, fields);
		
		return new ResponseEntity<Object>(contact, HttpStatus.OK);
	}
}
