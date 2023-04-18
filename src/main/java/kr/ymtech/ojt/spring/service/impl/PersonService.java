package kr.ymtech.ojt.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.ymtech.ojt.spring.dao.impl.PersonDAO;
import kr.ymtech.ojt.spring.dto.PersonDTO;
import kr.ymtech.ojt.spring.service.IPersonService;
import kr.ymtech.ojt.spring.vo.PersonVO;

/**
 * 사용자 관리
 * 
 * @author yblee
 * @since 2023.04.06
 */
@Service
public class PersonService implements IPersonService {

    @Autowired
    @Qualifier("personDAO")
    private PersonDAO personDAO;
    private PersonVO personVO;

    /**
     * @see IPersonService#deletePersonInfo(String)
     * 
     * @author yblee
     * @since 2023.04.10
     */
    @Override
    public boolean deletePersonInfo(String id) {
        boolean flag = personDAO.deletePersonInfo(id);
        return flag;
    }

    /**
     * @see IPersonService#findPersonByEmail(String)
     * 
     * @author yblee
     * @since 2023.04.06
     */
    @Override
    public List<String> findPersonByEmail(String email) {
        List<String> findPersonEmail = personDAO.findPersonByEmail(email);
        if (findPersonEmail.isEmpty()) {
            return null;
        } else {
            return findPersonEmail;
        }
    }

    /**
     * @see IPersonService#insertPersonInfo(PersonDTO)
     * 
     * @author yblee
     * @since 2023.04.10
     */
    @Override
    public boolean insertPersonInfo(PersonDTO person) {
        boolean flag = personDAO.insertPersonInfo(convertDTO2VO(person));
        return flag;
    }

    /**
     * @see IPersonService#findPersonById(String)
     * 
     * @author yblee
     * @since 2023.04.06
     */
    @Override
    public List<String> findPersonById(String id) {
        List<String> findPersonId = personDAO.findPersonById(id);
        if (findPersonId.isEmpty()) {
            return null;
        } else {
            return findPersonId;
        }
    }

    /**
     * @see IPersonService#findPersonAll()
     * 
     * @author yblee
     * @since 2023.04.14
     */
    @Override
    public List<String> findPersonAll() {
        List<String> findPersonAll = personDAO.findPersonAll();
        if (findPersonAll.isEmpty()) {
            return null;
        } else {
            return findPersonAll;
        }
    }

    /**
     * @see IPersonService#updatePersonInfoSet(String, PersonDTO)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public String updatePersonInfoSet(String id, PersonDTO updatePerson) {
        convertDTO2VO(updatePerson);
        boolean flag = personDAO.updatePersonInfoSet(id, personVO);
        if (flag) {
            PersonDTO updatedPerson = new PersonDTO();
            updatedPerson.setId(updatePerson.getId());
            updatedPerson.setAge(updatePerson.getAge());
            updatedPerson.setEmail(updatePerson.getEmail());
            updatedPerson.setName(updatePerson.getName());
            return updatePerson.toString();
        } else {
            System.out.println("사용자 정보 수정에 실패하였습니다.");
            return null;
        }
    }

    public PersonVO convertDTO2VO(PersonDTO personDTO) {
        personVO.setId(personDTO.getId());
        personVO.setName(personDTO.getName());
        personVO.setAge(personDTO.getAge());
        personVO.setEmail(personDTO.getEmail());
        return personVO;
    }
}
