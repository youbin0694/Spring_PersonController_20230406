package kr.ymtech.ojt.spring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ymtech.ojt.spring.entity.Person;
import kr.ymtech.ojt.spring.service.PersonService;

/**
 * 사용자 기능 관리
 * 
 * @author yblee
 * @since 2023.04.06
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonService personServ;

    /**
     * Name과 일치하는 사용자 반환
     * 
     * @param name 요청한 사용한 Name
     * @return Name과 일치하는 사용자
     * 
     * @author yblee
     * @since 2023.04.06
     */
    @GetMapping("/{name}")
    public List<Person> personByName(@PathVariable String name) {
        return personServ.personByName(name);
    }

    /**
     * email과 일치하는 사용자 반환
     * 
     * @param name 요청한 사용한 Email
     * @return Email과 일치하는 사용자
     * 
     * @author yblee
     * @since 2023.04.06
     */
    @GetMapping("")
    public List<Person> personByEmail(@RequestParam String email) {
        return personServ.personByName(email);
    }
}
