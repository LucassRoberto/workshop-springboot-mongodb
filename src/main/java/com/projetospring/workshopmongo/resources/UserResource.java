package com.projetospring.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetospring.workshopmongo.domain.User;
import com.projetospring.workshopmongo.dto.UserDTO;
import com.projetospring.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;

	@RequestMapping(method=RequestMethod.GET) //poderia ser utilizado o @GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll(); //buscar usuários no banco de dados e salvar na lista
		List<UserDTO> listDTO = list.stream()
				.map(x -> new UserDTO(x)).collect(Collectors.toList()); //converter cada objeto da lista original para um DTO
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //poderia ser utilizado o @GetMapping
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method=RequestMethod.POST) //poderia ser utilizado o @PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //para gerar cabeçalho com a url do novo recurso criado
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) //poderia ser utilizado o @GetMapping
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
