package com.nguyenduybinh.flyway.services;

import java.util.Map;

import com.nguyenduybinh.flyway.entities.Contact;

public interface ContactService {

	Contact patch(Long id, Map<String, Object> fields);
}
