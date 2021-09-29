package com.nguyenduybinh.flyway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenduybinh.flyway.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

}
