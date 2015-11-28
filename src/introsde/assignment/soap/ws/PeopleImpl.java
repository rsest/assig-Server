package introsde.assignment.soap.ws;
import java.util.List;

import javax.jws.WebService;

import introsde.assignment.soap.bean.PersonBean;
import introsde.assignment.soap.mapping.PersonBeanDelegate;
import introsde.assignment.soap.model.Person;

//Service Implementation

@WebService(endpointInterface = "introsde.assignment.soap.ws.People",
    serviceName="PeopleService")
public class PeopleImpl implements People {

    @Override
    public Person readPerson(Long id) {
        System.out.println("---> Reading Person by id = "+id);
        Person p = Person.getPersonById(id);
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getFirstname());
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
        return p;
    }

    @Override
    public List<PersonBean> getPeople() {
    	List<PersonBean> pl = PersonBeanDelegate.mapFromPersonList(Person.getAll());
    	return pl;
    }

    @Override
    public Long addPerson(Person person) {
        Person.savePerson(person);
        return person.getId();
    }

    @Override
    public Long updatePerson(PersonBean person) {
    	Person p = PersonBeanDelegate.mapToPerson(person);
        Person.updatePerson(p);
        return person.getId();
    }

    @Override
    public int deletePerson(Long id) {
        Person p = Person.getPersonById(id);
        if (p!=null) {
            Person.removePerson(p);
            return 0;
        } else {
            return -1;
        }
    }



}