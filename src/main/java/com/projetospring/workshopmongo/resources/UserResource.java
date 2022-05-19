package com.projetospring.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.workshopmongo.domain.User;
import com.projetospring.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;

	@RequestMapping(method=RequestMethod.GET) //poderia ser utilizado o @GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll(); //buscar usuários no banco de dados e salvar na lista
		return ResponseEntity.ok().body(list);
	}
}
