package com.nguyenduybinh.flyway.services.impl;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.nguyenduybinh.flyway.entities.Contact;
import com.nguyenduybinh.flyway.repositories.ContactRepository;
import com.nguyenduybinh.flyway.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public Contact patch(Long id, Map<String, Object> fields) {
		Optional<Contact> contact = contactRepository.findById(id);
		if(contact.isPresent()) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Contact.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, contact.get(), value);
			});
			Contact updatedContact = contactRepository.saveAndFlush(contact.get());
			return updatedContact;
		}
		return null;
	}
}
