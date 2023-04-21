package kr.ymtech.ojt.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.ymtech.ojt.spring.dao.impl.PersonDAO;
import kr.ymtech.ojt.spring.dto.PersonDTO;
import kr.ymtech.ojt.spring.dto.UpdatePersonDTO;
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
    public PersonVO findPersonByEmail(String email) {
        PersonVO findPersonEmail = personDAO.findPersonByEmail(email);
        return findPersonEmail;
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
    public PersonVO findPersonById(String id) {
        PersonVO findPersonId = personDAO.findPersonById(id);
        return findPersonId;
    }

    /**
     * @see IPersonService#findPersonAll()
     * 
     * @author yblee
     * @since 2023.04.14
     */
    @Override
    public List<PersonVO> findPersonAll() {
        List<PersonVO> findPersonAll = personDAO.findPersonAll();
        return findPersonAll;
    }

    /**
     * @see IPersonService#updatePersonInfoSet(String, PersonDTO)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public UpdatePersonDTO updatePersonInfoSet(String id, PersonDTO updatePerson) {
        // 수정 전 사용자 정보 찾기
        PersonVO personVO = personDAO.findPersonById(id);
        // 사용자 정보가 존재한다면
        if (personVO != null) {
            // 수정 전 사용자 정보 VO 객체 DTO convert
            PersonDTO personDTO = convertVO2DTO(personVO);
            // 수정 전/후 정보 담을 updatePersonDTO 객체
            UpdatePersonDTO updatePersonDTO = new UpdatePersonDTO();
            // Old에 수정 전 정보 set
            updatePersonDTO.setOld(personDTO);

            // 수정 할 사용자 정보 VO convert
            personVO = convertDTO2VO(updatePerson);
            // 수정 성공했다면 flag = true
            boolean flag = personDAO.updatePersonInfoSet(id, personVO);

            if (flag) {
                PersonDTO updatedPerson = new PersonDTO();
                updatedPerson.setId(personDTO.getId());
                updatedPerson.setAge(updatePerson.getAge());
                updatedPerson.setEmail(updatePerson.getEmail());
                updatedPerson.setName(updatePerson.getName());
                updatePersonDTO.setUpdate(updatedPerson);
                return updatePersonDTO;
            } else {
                System.out.println("사용자 정보 수정에 실패하였습니다.");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * DTO TO VO 변환
     * 
     * @param personDTO DTO 객체
     * @return VO 객체
     * 
     * @author yblee
     * @since 2023.04.20
     */
    public PersonVO convertDTO2VO(PersonDTO personDTO) {
        PersonVO personVO = new PersonVO();
        personVO.setId(personDTO.getId());
        personVO.setName(personDTO.getName());
        personVO.setAge(personDTO.getAge());
        personVO.setEmail(personDTO.getEmail());
        return personVO;
    }

    /**
     * VO TO DTO 변환
     * 
     * @param personVO VO 객체
     * @return DTO 객체
     * 
     * @author yblee
     * @since 2023.04.20
     */
    public PersonDTO convertVO2DTO(PersonVO personVO) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(personVO.getId());
        personDTO.setName(personVO.getName());
        personDTO.setAge(personVO.getAge());
        personDTO.setEmail(personVO.getEmail());
        return personDTO;
    }
}
