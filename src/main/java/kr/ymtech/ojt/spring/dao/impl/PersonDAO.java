package kr.ymtech.ojt.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ymtech.ojt.spring.dao.IPersonDAO;
import kr.ymtech.ojt.spring.vo.PersonVO;

/**
 * 사용자 DB 연동 관리
 * 
 * @author yblee
 * @since 2023.04.13
 */
@Repository
public class PersonDAO implements IPersonDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private PersonVO personVO;
    private PersonRowMapper personRowMapper;

    public PersonDAO() {
        personVO = new PersonVO();
        personRowMapper = new PersonRowMapper();
    }

    /**
     * @see IPersonDAO#deletePersonInfo(String)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public boolean deletePersonInfo(String id) {
        boolean flag = this.jdbcTemplate.update("DELETE FROM user WHERE id = ?", id) > 0;
        return flag;
    }

    /**
     * @see IPersonDAO#findPersonAll()
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public List<PersonVO> findPersonAll() {
        List<PersonVO> personList = this.jdbcTemplate.query("SELECT * FROM user", personRowMapper);
        return personList;
    }

    /**
     * @see IPersonDAO#findPersonByEmail(String)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public PersonVO findPersonByEmail(String email) {
        this.personVO = this.jdbcTemplate.queryForObject("SELECT * from user WHERE email = ?", personRowMapper,
                email);
        return this.personVO;
    }

    /**
     * @see IPersonDAO#insertPersonInfo(PersonVO)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public boolean insertPersonInfo(PersonVO person) {
        boolean flag = this.jdbcTemplate.update("INSERT INTO user VALUES (?, ?, ?, ?)", person.getId(),
                person.getName(),
                person.getAge(), person.getEmail()) > 0;

        return flag;
    }

    /**
     * @see IPersonDAO#findPersonById(String)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public PersonVO findPersonById(String id) {
        this.personVO = this.jdbcTemplate.queryForObject("SELECT * from user WHERE id = ?", personRowMapper, id);
        return this.personVO;
    }

    /**
     * @see IPersonDAO#updatePersonInfoSet(String, PersonVO)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public boolean updatePersonInfoSet(String id, PersonVO personVO) {
        boolean flag = this.jdbcTemplate.update("UPDATE user SET name=?, age=?, email=? WHERE id=?", personVO.getName(),
                personVO.getAge(), personVO.getEmail(), id) > 0;

        return flag;
    }

    /**
     * 
     * 
     * @author yblee
     * @since 2023.04.19
     */
    public class PersonRowMapper implements RowMapper<PersonVO> {
        @Override
        public PersonVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            PersonVO personVO = new PersonVO();
            personVO.setId(rs.getString("id"));
            personVO.setAge(rs.getInt("age"));
            personVO.setName(rs.getString("name"));
            personVO.setEmail(rs.getString("email"));
            return personVO;
        }
    }
}
