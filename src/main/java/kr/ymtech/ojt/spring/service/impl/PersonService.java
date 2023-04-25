package kr.ymtech.ojt.spring.service.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.github.mjyoun.core.data.Result;
import kr.ymtech.ojt.spring.dto.PersonDTO;
import kr.ymtech.ojt.spring.repository.PersonRepository;
import kr.ymtech.ojt.spring.service.IPersonService;
import kr.ymtech.ojt.spring.vo.PersonVO;

/**
 * 사용자 관리
 * 
 * @author yblee
 * @since 2023.04.06
 */
@Service(PersonService.QUALIFIER_BEAN)
public class PersonService implements IPersonService {

    public static final String QUALIFIER_BEAN = "kr.ymtech.ojt.spring.service.impl.PersonService";

    private PersonRepository personRepository;

    /**
     * (non-javadoc)
     * 
     * @param personRepository
     * 
     * @author yblee
     * @since 2023.04.24
     */
    public PersonService(@Qualifier(PersonRepository.QUALIFIER_BEAN) PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * @see IPersonService#deletePersonInfo(String)
     * 
     * @author yblee
     * @since 2023.04.10
     */
    @Override
    public Result<Boolean> deletePersonInfo(@Valid String id) {
        Result<Boolean> result = null;
        Optional<PersonVO> personOp = this.personRepository.findById(id);
        if (!personOp.isPresent()) {
            String msg = "존재하지 않는 아이디 입니다.";
            System.out.println(msg);
            result = Result.error(msg);
        } else {
            this.personRepository.deleteById(id);
            result = Result.ok(true);
        }
        return result;
    }

    /**
     * @see IPersonService#findPersonByEmail(String)
     * 
     * @author yblee
     * @since 2023.04.06
     */
    @Override
    public Result<PersonVO> findPersonByEmail(@Valid String email) {
        Result<PersonVO> result = null;
        Optional<PersonVO> personOp = this.personRepository.findByEmail(email);
        if (!personOp.isPresent()) {
            String msg = "존재하지 않는 이메일 입니다.";
            System.out.println(msg);
            result = Result.error(msg);
        } else {
            PersonVO personVO = personOp.get();
            result = Result.ok(personVO);
        }
        return result;
    }

    /**
     * @see IPersonService#insertPersonInfo(PersonDTO)
     * 
     * @author yblee
     * @since 2023.04.10
     */
    @Override
    public Result<Boolean> insertPersonInfo(@Valid PersonDTO person) {
        Result<Boolean> result = null;
        Optional<PersonVO> personOp = this.personRepository.findById(person.getId());
        if (personOp.isPresent()) {
            String msg = "존재하는 ID 입니다.";
            System.out.println(msg);
            result = Result.error(msg);
        } else {
            PersonVO personVO = this.convertDTO2VO(person);
            this.personRepository.save(personVO);
            result = Result.ok(true);
        }
        return result;
    }

    /**
     * @see IPersonService#findPersonById(String)
     * 
     * @author yblee
     * @since 2023.04.06
     */
    @Override
    public Result<PersonVO> findPersonById(@Valid String id) {
        Result<PersonVO> result = null;
        Optional<PersonVO> personOp = this.personRepository.findById(id);
        if (!personOp.isPresent()) {
            String msg = "존재하지 않는 아이디 입니다.";
            System.out.println(msg);
            result = Result.error(msg);
        } else {
            PersonVO personVO = personOp.get();
            result = Result.ok(personVO);
        }
        return result;
    }

    /**
     * @see IPersonService#findPersonAll()
     * 
     * @author yblee
     * @since 2023.04.14
     */
    @Override
    @SuppressWarnings("unchecked")
    public Result<PersonVO> findPersonAll() {
        Result<PersonVO> result = null;
        // TODO 어떻게?
        result = (Result<PersonVO>) this.personRepository.findAll();
        return result;
    }

    /**
     * @see IPersonService#updatePersonInfoSet(String, PersonDTO)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public Result<Boolean> updatePersonInfoSet(@Valid String id, @Valid PersonDTO updatePerson) {
        Result<Boolean> result = null;
        Optional<PersonVO> personOp = this.personRepository.findById(id);
        if (personOp.isPresent()) {
            String msg = "존재하는 ID 입니다.";
            System.out.println(msg);
            result = Result.error(msg);
        } else {
            PersonVO personVO = this.convertDTO2VO(updatePerson);
            this.personRepository.save(personVO);
            result = Result.ok(true);
        }
        return result;
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
    public PersonVO convertDTO2VO(@Valid PersonDTO personDTO) {
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
    public PersonDTO convertVO2DTO(@Valid PersonVO personVO) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(personVO.getId());
        personDTO.setName(personVO.getName());
        personDTO.setAge(personVO.getAge());
        personDTO.setEmail(personVO.getEmail());
        return personDTO;
    }
}
