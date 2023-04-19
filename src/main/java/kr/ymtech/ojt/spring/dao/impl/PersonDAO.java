package kr.ymtech.ojt.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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
    private List<PersonVO> personInfo;
    private List<String> personInfoString;

    /**
     * @see IPersonDAO#deletePersonInfo(String)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public boolean deletePersonInfo(String id) {
        boolean flag = false;
        if (this.jdbcTemplate.update("DELETE FROM user WHERE id = ?", id) > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * @see IPersonDAO#findPersonAll()
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public List<String> findPersonAll() {
        this.personInfo = this.jdbcTemplate.query("SELECT * FROM user", new PersonRowMapper());
        if (this.personInfo.isEmpty()) {
            return null;
        } else {
            for (PersonVO p : this.personInfo) {
                this.personInfoString.add(p.toString());
            }
            return this.personInfoString;
        }
    }

    /**
     * @see IPersonDAO#findPersonByEmail(String)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public List<String> findPersonByEmail(String email) {
        this.personInfo = this.jdbcTemplate.query("SELECT * from user WHERE email = ?", new PersonRowMapper(), email);
        if (this.personInfo.isEmpty()) {
            return null;
        } else {
            this.personInfoString = Arrays.asList(this.personInfo.get(0).toString());
            return this.personInfoString;
        }
    }

    /**
     * @see IPersonDAO#insertPersonInfo(PersonVO)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public boolean insertPersonInfo(PersonVO person) {
        boolean flag = false;
        if (this.jdbcTemplate.update("INSERT INTO user VALUES (?, ?, ?, ?)", person.getId(), person.getName(),
                person.getAge(), person.getEmail()) > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * @see IPersonDAO#findPersonById(String)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public List<String> findPersonById(String id) {
        this.personInfo = this.jdbcTemplate.query("SELECT * from user WHERE id = ?", new PersonRowMapper(), id);
        if (this.personInfo.isEmpty()) {
            return null;
        } else {
            this.personInfoString = Arrays.asList(this.personInfo.get(0).toString());
            return this.personInfoString;
        }
    }

    /**
     * @see IPersonDAO#updatePersonInfoSet(String, PersonVO)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public boolean updatePersonInfoSet(String id, PersonVO personVO) {
        boolean flag = false;
        if (this.jdbcTemplate.update("UPDATE user SET name=?, age=?, email=? WHERE id=?", personVO.getName(),
                personVO.getAge(), personVO.getEmail(), id) > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 
     * 
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
