package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.dto.PersonDTO;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2 // --> võimaldab logida. vs sout: annab kellaaja+kuupäeva, info/error/debug, faili kus logis, logifaili
//@CrossOrigin("http://localhost:4200")
@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper; // selle Autowire-damiseks pidime @Beani sees tegema = new ModelMapper()


    @PostMapping("signup")
    public ResponseEntity<List<Person>> signup(@RequestBody Person person) {
        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(personRepository.findAll()); // ResponseEntity --> võimaldab seadistada Headereid ja staatuskoodi
    } // ehk rohkem kontrolli enda käes

//    @GetMapping("persons")
//    public List<PersonDTO> getPersons() {
//        List<Person> persons = personRepository.findAll();
//        List<PersonDTO> personDTOList = new ArrayList<>();
//        for (Person p : persons) {
//            PersonDTO person = new PersonDTO();
//            person.setEmail(p.getEmail());
//            person.setFirstName(p.getFirstName());
//            person.setLastName(p.getLastName());
//            personDTOList.add(person);
//        }
//        return personDTOList;
//    }

    @GetMapping("persons")
    public ResponseEntity<List<PersonDTO>> getPersons() {
        log.info("User performed receiving persons API endpoint");
        List<Person> persons = personRepository.findAll();
//        ModelMapper modelMapper = new ModelMapper(); --> selle asemel @Autowired
        log.info(modelMapper);
        return ResponseEntity.ok().body(List.of(modelMapper.map(persons, PersonDTO[].class)));
    }
}
