package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOv2;
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;

@RestController
@RequestMapping("api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices personServices;
	
	
	
	@GetMapping(value="/{id}",produces= {MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML
	})
	public PersonVO findById(
			@PathVariable(value="id")Long id) {
	return personServices.findById(id);
		
	}
	@DeleteMapping(value="/{id}",produces={MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML, "application/x-yaml"
	})
	public ResponseEntity<?> delete(@PathVariable(value="id")Long id) {
		personServices.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	@PostMapping(consumes={MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML, "application/x-yaml"
	},
			produces={MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML, "application/x-yaml"
			})
	public PersonVO create(
			@RequestBody PersonVO person) {
		return personServices.create(person);
		
	}
	@PostMapping(value="/v2",  consumes={MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML, "application/x-yaml"
	},
			produces={MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML, "application/x-yaml"
			})
	public PersonVOv2 createV2(
			@RequestBody PersonVOv2 person) {
		return personServices.createV2(person);
		
	}
	@PutMapping(consumes={MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML, "application/x-yaml"
	},
			produces={MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML, "application/x-yaml"
			})
	public PersonVO update(
			@RequestBody PersonVO person) {
		return personServices.create(person);
		
	}
	@GetMapping(produces={MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML,"application/x-yaml"
	})
	public List <PersonVO> findAll(){
		return personServices.findAll();
		
	}


}
