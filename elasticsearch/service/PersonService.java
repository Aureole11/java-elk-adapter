package com.elk.elasticsearch.service;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.elk.elasticsearch.document.Person;
import com.elk.elasticsearch.repository.PersonRepository;

@Service
public class PersonService {

	private final PersonRepository repository;
	
	@Autowired
	public PersonService(PersonRepository repository) {
		this.repository = repository;
	}
	
	public void save(final Person person) {
		repository.save(person);
	}
	
	public Iterable<Person> getAllPerson() {
		return  repository.findAll(); 
	}
	
	public Optional<Person> findById(final String id) {
		return repository.findById(id);
	}
	
	public List<Person> findByOrderByTimestampDesc() {
		Iterable<Person> sort = repository.findAll(Sort.by(Sort.Direction.DESC, "timestamp")); 
		for(Person p:sort) {
//			return repository.findByTimestamp(p.getTimestamp());
			Date lt = p.getTimestamp();
			Temporal time = java.time.Instant.now(); 
			java.time.Instant dt = lt.toInstant();
			Duration t = Duration.between(dt, time);
			if(t.toMinutes()>60) {
				System.out.println("No logs update in last one hour");
				break;
		    }else {
		    	System.out.println(dt);
		    	System.out.println("current time: "+time);
		    	return repository.findByTimestamp(lt);
		    }
		}
		return null;
	}
	
}
