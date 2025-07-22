package github.com._mk;

import github.com._mk.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person create(Person person) {
        logger.info("CREATING ONE PERSON!");

        return person;
    }

    public Person update(Person person) {
        logger.info("UPDATING ONE PERSON!");

        return person;
    }

    public void delete(String id) {
        logger.info("DELETING ONE PERSON!");
    }

    public List<Person> findAll() {
        logger.info("FINDING ALL PEOPLE!");

        List<Person> persons = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id) {
        logger.info("FINDING ONE PERSON!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("João Marcos");
        person.setLastName("de Jesus");
        person.setAddress("São Paulo - São Paulo - Brasil");
        person.setGender("Male");

        return person;

    }


    private Person mockPerson(int i) {

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName " + i);
        person.setLastName("LastName " + i);
        person.setAddress("some address in Brasil");
        if (i % 2 == 0) {
            person.setGender("Male");
        } else {
            person.setGender("Female");
        }
        return person;

    }
}
