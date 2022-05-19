package com.projetospring.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.workshopmongo.domain.User;
import com.projetospring.workshopmongo.dto.UserDTO;
import com.projetospring.workshopmongo.repository.UserRepository;
import com.projetospring.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll(); //buscar todos os objetos do tipo user no banco de dados
	}
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public User fromDTO (UserDTO objDTO) { //novo usuário recebendo dados do DTO como parâmetro
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
