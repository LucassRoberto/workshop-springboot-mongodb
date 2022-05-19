package com.projetospring.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.workshopmongo.domain.User;
import com.projetospring.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll(); //buscar todos os objetos do tipo user no banco de dados
	}
}
