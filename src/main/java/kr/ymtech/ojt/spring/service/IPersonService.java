package kr.ymtech.ojt.spring.service;

import java.util.List;

import kr.ymtech.ojt.spring.dto.PersonDTO;

/**
 * 사용자 관리
 * 
 * @author yblee
 * @since 2023.04.13
 */
public interface IPersonService {

    /**
     * id 해당 사용자 반환
     * 
     * @param id 사용자 id
     * @return id과 일치하는 사용자
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public List<String> findPersonById(String id);

    /**
     * 전체 사용자 반환
     * 
     * @return 전체 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.14
     */
    public List<String> findPersonAll();

    /**
     * email 해당 사용자 반환
     * 
     * @param email 사용자 Email
     * @return Email 일치하는 사용자
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public List<String> findPersonByEmail(String email);

    /**
     * 사용자 정보 입력
     * 
     * @param person 사용자 정보
     * @return 새로 등록된 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public boolean insertPersonInfo(PersonDTO person);

    /**
     * 입력된 ID 가진 사용자 삭제
     * 
     * @param id 사용자 ID
     * @return 삭제된 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public boolean deletePersonInfo(String id);

    /**
     * 사용자 정보 수정
     * 
     * @param id           사용자 id
     * @param updatePerson 업데이트 할 사용자 객체
     * @return 업데이트 한 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public String updatePersonInfoSet(String id, PersonDTO updatePerson);
}
