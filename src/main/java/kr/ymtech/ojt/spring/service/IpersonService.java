package kr.ymtech.ojt.spring.service;

import java.util.List;

import kr.ymtech.ojt.spring.dto.PersonDTO;

public interface IpersonService {
    public PersonDTO personById(String id);

    public PersonDTO findPersonByEmail(String email);

    public PersonDTO insertPersonInfo(PersonDTO person);

    public PersonDTO deletePersonInfo(String id);

    public List<PersonDTO> updatePersonInfo(String id, PersonDTO updatePerson);

    public PersonDTO findPerson(List<PersonDTO> personList, String id);

    public int findPersonIndex(String id);
}
