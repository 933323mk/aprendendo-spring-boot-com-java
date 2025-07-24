package github.com._mk.services;

import github.com._mk.exception.ResourceNotFoundException;
import github.com._mk.model.Person;
import github.com._mk.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public Person create(Person person) {
        logger.info("CREATING ONE PERSON!");

        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("UPDATING ONE PERSON!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("NOT RECORDS FOUND FOR THIS ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {
        logger.info("DELETING ONE PERSON!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOT RECORDS FOUND FOR THIS ID"));

        repository.delete(entity);
    }

    public List<Person> findAll() {
        logger.info("FINDING ALL PEOPLE!");

        return repository.findAll();
    }

    public Person findById(Long id) {
        logger.info("FINDING ONE PERSON!");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOT RECORDS FOUND FOR THIS ID"));

    }
}
