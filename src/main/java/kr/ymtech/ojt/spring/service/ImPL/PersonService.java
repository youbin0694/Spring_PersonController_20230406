package kr.ymtech.ojt.spring.service.ImPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.ymtech.ojt.spring.dto.PersonDTO;
import kr.ymtech.ojt.spring.service.IpersonService;

/**
 * 사용자 관리
 * 
 * @author yblee
 * @since 2023.04.06
 */
@Service
public class PersonService implements IpersonService {

    private List<PersonDTO> personList;

    /**
     * 입력된 ID 가진 사용자 삭제
     * 
     * @param id 사용자 ID
     * @return 삭제된 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.10
     */
    @Override
    public PersonDTO deletePersonInfo(String id) {
        PersonDTO person = this.findPerson(this.personList, id);

        if (person == null) {
            return null;
        } else {
            this.personList.remove(person);
            return person;
        }
    }

    /**
     * id에 해당하는 사용자 객체 반환하는 메서드
     * 
     * 
     * @param personList 사용자 정보 리스트
     * @param id         사용자 정보 id
     * @return 사용자 객체
     * 
     * @author yblee
     * @since 2023.04.10
     */
    @Override
    public PersonDTO findPerson(List<PersonDTO> personList, String id) {
        for (PersonDTO person : personList) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

    /**
     * email 해당 사용자 반환 함수
     * 
     * @param email 사용자 Email
     * @return Email 일치하는 사용자
     * 
     * @author yblee
     * @since 2023.04.06
     */
    @Override
    public PersonDTO findPersonByEmail(String email) {
        PersonDTO person = this.findPerson(this.personList, email);
        if (person == null) {
            return null;
        } else {
            return person;
        }
    }

    /**
     * 사용자 인덱스 찾는 메서드
     * 
     * @param id 사용자 ID
     * @return 해당 사용자 인덱스
     * 
     * @author yblee
     * @since 2023.04.11
     */
    @Override
    public int findPersonIndex(String id) {
        int index = 0;
        for (int i = 0; i < this.personList.size(); i++) {
            if (this.personList.get(i).getId().equals(id)) {
                index = i;
            }
        }
        return index;
    }

    /**
     * 사용자 정보 입력 메서드
     * 
     * @param person 사용자 정보
     * @return 새로 등록된 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.10
     */
    @Override
    public PersonDTO insertPersonInfo(PersonDTO person) {
        if (person != null) {
            if (this.findPerson(this.personList, person.getId()) == null) {
                this.personList.add(person);
                return person;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * id 해당 사용자 반환 함수
     * 
     * @param id 사용자 id
     * @return id과 일치하는 사용자
     * 
     * @author yblee
     * @since 2023.04.06
     */
    @Override
    public PersonDTO personById(String id) {
        PersonDTO findPersonId = this.findPerson(this.personList, id);
        if (findPersonId == null) {
            return null;
        } else {
            this.personList.add(findPersonId);
            return findPersonId;
        }
    }

    /**
     * 사용자 정보 수정하는 메서드
     * 
     * @param id           사용자 id
     * @param updatePerson 업데이트 할 사용자 객체
     * @return 업데이트 한 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.10
     */
    @Override
    public List<PersonDTO> updatePersonInfo(String id, PersonDTO updatePerson) {
        List<PersonDTO> updatePersonList = new ArrayList<>();
        PersonDTO updatedPersonBefore = null;
        PersonDTO updatedPersonAfter = new PersonDTO();

        updatedPersonBefore = this.findPerson(this.personList, id);

        if (updatedPersonBefore == null) {
            return null;
        }

        updatePersonList.add(updatedPersonBefore);
        updatedPersonAfter = this.findPerson(this.personList, id);
        updatedPersonAfter.setAge(updatePerson.getAge());
        updatedPersonAfter.setEmail(updatePerson.getEmail());
        updatedPersonAfter.setName(updatePerson.getName());
        updatePersonList.add(updatedPersonAfter);
        return updatePersonList;
    }

}
