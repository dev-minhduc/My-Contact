package com.devjava.service;

import java.util.List;

import com.devjava.domain.Contact;

//Xử lí các business logic trong App, chứa các phương thức sử dụng trong Controller
public interface ContactService {
	
	Iterable<Contact> findAll();
	
	List<Contact> search(String q);
	
	Contact findOne(int id);
	
	void save(Contact contact);
	void delete(int id);
}
