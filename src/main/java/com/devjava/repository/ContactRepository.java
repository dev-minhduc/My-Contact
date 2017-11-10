package com.devjava.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.devjava.domain.Contact;

//Interface thao tác CSDL
public interface ContactRepository extends CrudRepository<Contact, Integer>{
	//Phương thức tìm kiếm liên hệ có name LIKE %name%
	List<Contact> findByNameContaining(String q);
}	
