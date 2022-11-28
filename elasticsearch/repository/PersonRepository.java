package com.elk.elasticsearch.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.elk.elasticsearch.document.Person;

public interface PersonRepository extends ElasticsearchRepository<Person, String>{

	List<Person> findByOrderByTimestampDesc(Iterable<Person> sort);
	
	List<Person> findByTimestamp(Date dt);



	


}
