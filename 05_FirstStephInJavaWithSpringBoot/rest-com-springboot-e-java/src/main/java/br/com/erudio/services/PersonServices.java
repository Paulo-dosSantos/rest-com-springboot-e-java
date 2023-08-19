package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	private PersonRepository repository;
	
	private Logger logger=Logger.getLogger(PersonServices.class.getName());
	

	
	public List <PersonVO> findAll() {

		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		logger.info("Buscando uma pessoa");
	
		
		var entity= repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Recurso não encontrado"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	public PersonVO create(PersonVO person) {
		logger.info("Criando uma pessoa");
		var entity= DozerMapper.parseObject(person, Person.class);
		var vo= DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	public void delete(Long id) {
		logger.info("deletando uma pessoa");
		
		var entity= repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Recurso não encontrado"));
		repository.delete(entity);
		
	}
	public PersonVO update(PersonVO person) {
		logger.info("Atualizando uma pessoa");
		
		var entity= repository.findById(person.getId()).orElseThrow(()->new ResourceNotFoundException("Recurso não encontrado"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo=DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo ;
	}

}
