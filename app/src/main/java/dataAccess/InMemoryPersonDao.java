package dataAccess;

import entity.Person;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPersonDao implements PersonDao {
	
	  private List<Person> personList;

	    public InMemoryPersonDao() {
	        personList = new ArrayList<>();
	    }


	@Override
	public void addPerson(Person person) {
        personList.add(person);
		
	}

	@Override
	public void updatePerson(Person person) {
        for (Person p : personList) {
            if (p.getName().equals(person.getName()) && p.getSurname().equals(person.getSurname())) {
                p.setPhone(person.getPhone());
                p.setAddress(person.getAddress());
                p.setAge(person.getAge());
                break;
            }
        }
    }


	@Override
	  public void deletePerson(String name, String surname) {
        Person personToRemove = null;
        for (Person p : personList) {
            if (p.getName().equals(name) && p.getSurname().equals(surname)) {
                personToRemove = p;
                break;
            }
        }
        if (personToRemove != null) {
            personList.remove(personToRemove);
        }
    }

	@Override
	public List<Person> getAllPersons() {
		 return personList;
	}

	@Override
	 public Person getPersonByNameAndSurname(String name, String surname) {
        for (Person p : personList) {
            if (p.getName().equals(name) && p.getSurname().equals(surname)) {
                return p;
            }
        }
        return null;
    }
  
}
