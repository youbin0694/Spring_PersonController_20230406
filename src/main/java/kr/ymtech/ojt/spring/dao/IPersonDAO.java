package kr.ymtech.ojt.spring.dao;

import java.util.List;

import kr.ymtech.ojt.spring.vo.PersonVO;

/**
 * 사용자 DB 연동
 * 
 * @author yblee
 * @since 2023.04.13
 */
public interface IPersonDAO {
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
    public boolean insertPersonInfo(PersonVO person);

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
    public boolean updatePersonInfoSet(String id, PersonVO updatePerson);

    /**
     * 전체 사용자 반환
     * 
     * @return 전체 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public List<String> findPersonAll();

}
