package github.com._mk.services;

import github.com._mk.controllers.PersonController;
import github.com._mk.data.dto.v1.PersonDTO;
//import github.com._mk.data.dto.v2.PersonDTOV2;
import github.com._mk.exception.RequiredObjectIsNullException;
import github.com._mk.exception.ResourceNotFoundException;
import static github.com._mk.mapper.ObjectMapper.parseObject;
import static github.com._mk.mapper.ObjectMapper.parseListObjects;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//import github.com._mk.mapper.custom.PersonMapper;
import github.com._mk.model.Person;
import github.com._mk.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    //@Autowired
    //PersonMapper converter;

    public PersonDTO create(PersonDTO person) {
        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("CREATING ONE PERSON!");
        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTO.class);
        linksHateoas(dto);
        return dto;
    }

//    public PersonDTOV2 createV2(PersonDTOV2 person) {
//        if(person == null) throw new RequiredObjectIsNullException();
//        logger.info("CREATING ONE PERSON V2!");
//        var entity = converter.convertDTOToEntity(person);
//
//        return converter.convertEntityToDTO(repository.save(entity));
//    }

    public PersonDTO update(PersonDTO person) {
        if(person == null) throw new RequiredObjectIsNullException();
        logger.info("UPDATING ONE PERSON!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("NOT RECORDS FOUND FOR THIS ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(repository.save(entity), PersonDTO.class);
        linksHateoas(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("DELETING ONE PERSON!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOT RECORDS FOUND FOR THIS ID"));
        repository.delete(entity);
    }

    public List<PersonDTO> findAll() {
        logger.info("FINDING ALL PEOPLE!");

        var persons = parseListObjects(repository.findAll(), PersonDTO.class);
        persons.forEach(this::linksHateoas);
        return persons;
    }

    public PersonDTO findById(Long id) {
        logger.info("FINDING ONE PERSON!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOT RECORDS FOUND FOR THIS ID"));
        var dto = parseObject(entity, PersonDTO.class);
        linksHateoas(dto);
        return dto;

    }

    private void linksHateoas(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withRel("findById").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));

    }
}
