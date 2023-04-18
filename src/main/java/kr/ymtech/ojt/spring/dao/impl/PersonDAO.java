package kr.ymtech.ojt.spring.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/user_manage";
    private final String DB_ID = "ubin";
    private final String DB_PWD = "0694";
    private final String DB_PATH = "org.mariadb.jdbc.Driver";

    /**
     * @see IPersonDAO#deletePersonInfo(String)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public boolean deletePersonInfo(String id) {
        boolean flag = false;
        try {
            Class.forName(DB_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
                PreparedStatement stmt = con.prepareStatement("DELETE FROM user WHERE id = ?");) {

            stmt.setString(1, id);

            stmt.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println("삭제할 사용자가 존재하지 않습니다.");
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
        List<String> findAllPersonInfoList = new ArrayList<>();
        try {
            Class.forName(DB_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM user");
                ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                findAllPersonInfoList.add(String.format("ID: %s, Name: %s, Age: %d Email: %s", rs.getString("id"),
                        rs.getString("name"), rs.getInt("age"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println("전체 사용자 정보를 가져오지 못했습니다.");
        }
        return findAllPersonInfoList;
    }

    /**
     * @see IPersonDAO#findPersonByEmail(String)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public List<String> findPersonByEmail(String email) {
        List<String> findPersonInfoList = new ArrayList<>();
        try {
            Class.forName(DB_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
                PreparedStatement stmt = con.prepareStatement("SELECT * from user WHERE email = ?");) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                findPersonInfoList.add(String.format("ID: %s, Name: %s, Age: %d", rs.getString("id"),
                        rs.getString("name"), rs.getInt("age")));
            }
        } catch (SQLException e) {
            System.out.println("이메일을 찾을 수 없습니다.");
        }
        return findPersonInfoList;
    }

    /**
     * @see IPersonDAO#insertPersonInfo(PersonVO)
     * 
     * @author yblee
     * @since 2023.04.13
     */
    @Override
    public boolean insertPersonInfo(PersonVO person) {
        boolean flag = true;
        try {
            Class.forName(DB_PATH);
        } catch (ClassNotFoundException e) {
            System.out.println("DB 연동 오류가 발생했습니다.");
        }

        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
                PreparedStatement stmt = con
                        .prepareStatement(
                                "INSERT INTO user VALUES (?, ?, ?, ?)");) {

            stmt.setString(1, person.getId());
            stmt.setString(2, person.getName());
            stmt.setInt(3, person.getAge());
            stmt.setString(4, person.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("사용자 등록에 실패하였습니다.");
            flag = false;
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
        List<String> findPersonInfoList = new ArrayList<>();
        try {
            Class.forName(DB_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
                PreparedStatement stmt = con.prepareStatement("SELECT * from user WHERE id = ?");) {
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                findPersonInfoList.add(String.format("ID: %s, Name: %s, Age: %d Email: %s", rs.getString("id"),
                        rs.getString("name"), rs.getInt("age"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
        return findPersonInfoList;
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
        try {
            Class.forName(DB_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
                PreparedStatement stmt = con.prepareStatement("UPDATE user SET name=?, age=?, email=? WHERE id=?");) {

            stmt.setString(1, personVO.getName());
            stmt.setInt(2, personVO.getAge());
            stmt.setString(3, personVO.getEmail());
            stmt.setString(4, personVO.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            flag = false;
            System.out.println("사용자를 찾을 수 없습니다.");
        }
        return flag;
    }

}
