package kr.ymtech.ojt.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private Person[] persons;

    /**
     * 사용자 기본 정보
     * 
     * @author yblee
     * @since 2023.04.06
     */
    public PersonService() {
        this.persons = new Person[5];
        String[] personName = { "LYB", "LSH", "LYB", "AAA", "BBB" };
        String[] personEmail = { "ubin@naver.com", "shyun@naver.com", "youbin@naver.com", "aaa@naver.com",
                "bbb@naver.com" };
        String[] personId = { "ubin", "shyun", "youbin", "aaa", "bbb" };
        int[] personAge = { 12, 13, 14, 15, 16 };

        for (int i = 0; i < persons.length; i++) {
            persons[i] = new Person();
            this.persons[i].setName(personName[i]);
            this.persons[i].setEmail(personEmail[i]);
            this.persons[i].setAge(personAge[i]);
            this.persons[i].setId(personId[i]);
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
        List<Person> personList = new ArrayList<>();
        for (Person person : this.persons) {
            if (person.getName().equals(name)) {
                personList.add(person);
            }
        }
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
    public Person findPersonByEmail(String email) throws Exception {
        // Person person = null;
        int index = -1;

        if (isEmail(email)) {
            // person 목록에서 일치하는 이메일의 index 검색
            for (int i = 0; i < this.persons.length; i++) {
                if (this.persons[i].getEmail().equals(email)) {
                    index = i;
                }
            }
            return this.persons[index];
        } else {
            return this.persons[index];
        }
    }

    /**
     * 이메일 validation check
     * 
     * @param email 사용자 Email
     * @return valid 유무
     */
    public boolean isEmail(String email) {
        boolean flag = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            flag = true;
        }
        System.out.println(flag);

        return flag;
    }
}
