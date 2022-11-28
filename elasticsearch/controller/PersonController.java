package com.elk.elasticsearch.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elk.elasticsearch.document.Person;
import com.elk.elasticsearch.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {

	private final PersonService service;
	
	@Autowired
	public PersonController(PersonService service) {
		this.service =service;
	}
	
	@PostMapping
	public void save(@RequestBody final Person person) {
		service.save(person);
	}
	
	@GetMapping("/all")
	public Iterable<Person> getALLPerson() {
		return service.getAllPerson();
	}
	
	@GetMapping("/{id}")
	public Optional<Person> findById(@PathVariable final String id) {
		return service.findById(id);
	}
	
	@GetMapping("/time")
	public List<Person> getPersonByTimestamp() {

        List<Person> person= service.findByOrderByTimestampDesc();
        
        return person;
	}
}
	
