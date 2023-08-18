package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	private PersonRepository repository;
	
	private Logger logger=Logger.getLogger(PersonServices.class.getName());
	

	
	public List <Person> findAll() {

		return repository.findAll();
	}

	public Person findById(Long id) {
		logger.info("Buscando uma pessoa");
	
		
		return repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Recurso não encontrado"));
	}
	public Person create(Person person) {
		logger.info("Criando uma pessoa");
		return repository.save(person);
	}
	public void delete(Long id) {
		logger.info("deletando uma pessoa");
		
		Person entity= repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Recurso não encontrado"));
		repository.delete(entity);
		
	}
	public Person update(Person person) {
		logger.info("Atualizando uma pessoa");
		
		Person entity= repository.findById(person.getId())
				.orElseThrow(()->new ResourceNotFoundException("Recurso não encontrado"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}

}
