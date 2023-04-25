package kr.ymtech.ojt.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.ymtech.ojt.spring.vo.PersonVO;

/**
 * Person 관련 JPA Repository
 * 
 * @author yblee
 * @since 2023.04.24
 */
@Repository(PersonRepository.QUALIFIER_BEAN)
public interface PersonRepository extends JpaRepository<PersonVO, String> {

    public static final String QUALIFIER_BEAN = "kr.ymtech.ojt.spring.repository.PersonRepository";

    public Optional<PersonVO> findByEmail(@Param("email") String email);
}