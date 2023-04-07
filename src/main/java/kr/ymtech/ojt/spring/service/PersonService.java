package kr.ymtech.ojt.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.ymtech.ojt.spring.entity.Person;

/**
 * 사용자 관리
 * 
 * @author yblee
 * @since 2023.04.06
 */
@Service
public class PersonService {

    private Person[] person;

    /**
     * 사용자 기본 정보
     * 
     * @author yblee
     * @since 2023.04.06
     */
    public PersonService() {
        this.person = new Person[5];
        String[] personName = { "LYB", "LSH", "LYB", "AAA", "BBB" };
        String[] personEmail = { "ubin@naver.com", "shyun@naver.com", "youbin@naver.com", "aaa@naver.com",
                "bbb@naver.com" };
        String[] personId = { "ubin", "shyun", "youbin", "aaa", "bbb" };
        int[] personAge = { 12, 13, 14, 15, 16 };

        for (int i = 0; i < person.length; i++) {
            person[i] = new Person();
            this.person[i].setName(personName[i]);
            this.person[i].setEmail(personEmail[i]);
            this.person[i].setAge(personAge[i]);
            this.person[i].setId(personId[i]);
        }
    }

    /**
     * name 해당 사용자 반환 함수
     * 
     * @param name 사용자 Name
     * @return Name과 일치하는 사용자
     * 
     * @author yblee
     * @since 2023.04.06
     */
    public List<Person> personByName(String name) {
        System.out.println(name);
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < person.length; i++) {
            if (this.person[i].getName().equals(name)) {
                personList.add(this.person[i]);
            }
        }
        System.out.println(personList);
        return personList;
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
    public List<Person> personByEmail(String email) {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < person.length; i++) {
            if (this.person[i].getName().equals(email)) {
                personList.add(this.person[i]);
            }
        }
        return personList;
    }
}
