package github.com._mk.controllers;

import github.com._mk.controllers.docs.PersonControllerDocs;
import github.com._mk.data.dto.v1.PersonDTO;
//import github.com._mk.data.dto.v2.PersonDTOV2;
import github.com._mk.services.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing people")
public class PersonController implements PersonControllerDocs {

    @Autowired
    private final PersonServices service;

    public PersonController(PersonServices service) {
        this.service = service;
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE},
                produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE})
    @Override
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);
    }

//    @PostMapping(value = "/v2", consumes = {
//            MediaType.APPLICATION_JSON_VALUE,
//            MediaType.APPLICATION_XML_VALUE,
//            MediaType.APPLICATION_YAML_VALUE},
//            produces = {
//                    MediaType.APPLICATION_JSON_VALUE,
//                    MediaType.APPLICATION_XML_VALUE,
//                    MediaType.APPLICATION_YAML_VALUE})
//    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 person) {
//        return service.createV2(person);
//    }

    @PutMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE},
                produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE})
    @Override
    public PersonDTO update(@RequestBody PersonDTO person) {

        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE})

    @Override
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE})

    @Override
    public List<PersonDTO> findAll() {
        return service.findAll();
    }


//    @GetMapping(value = "/{id}", produces = {
//    MediaType.APPLICATION_JSON_VALUE,
//    MediaType.APPLICATION_XML_VALUE,
//    MediaType.APPLICATION_YAML_VALUE})
//    public PersonDTO findById(@PathVariable("id") Long id){
//        var person = service.findById(id);
//        person.setBirthDay(new Date());
//
//        return person;
//    }
}
