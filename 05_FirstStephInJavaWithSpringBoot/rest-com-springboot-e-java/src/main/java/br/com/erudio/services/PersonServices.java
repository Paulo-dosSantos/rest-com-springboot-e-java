package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOv2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonMapper mapper;
	
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
	public PersonVOv2 createV2(PersonVOv2 person) {
		logger.info("Criando uma pessoa com v2");
		var entity= mapper.convertVoToEntity(person);
		var vo= mapper.convertEntityToVo(repository.save(entity));
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
