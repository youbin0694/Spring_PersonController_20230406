package kr.ymtech.ojt.spring.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ymtech.ojt.spring.dto.PersonDTO;
import kr.ymtech.ojt.spring.service.PersonService;
import kr.ymtech.ojt.spring.util.Vaild;

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

    public PersonController() {
        this.personServ = new PersonService();
    }

    /**
     * ID와 일치하는 사용자 반환
     * 
     * @param id 요청한 사용한 ID
     * @return ID와 일치하는 사용자
     * 
     * @author yblee
     * @since 2023.04.06
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findPersonByName(@PathVariable String id) {
        if (personServ.personById(id) == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(personServ.personById(id));
        }
    }

    /**
     * email과 일치하는 사용자 반환
     * 
     * @param email 요청한 사용자 Email
     * @return Email과 일치하는 사용자
     * 
     * @author yblee
     * @since 2023.04.06
     */
    @GetMapping
    public ResponseEntity<PersonDTO> findPersonByEmail(@RequestParam(required = false) String email) {
        Vaild vaild = new Vaild();
        PersonDTO person = null;
        if (vaild.isEmail(email)) {
            person = personServ.findPersonByEmail(email);
        }
        if (person == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(person);
        }
    }

    /**
     * 새로운 사용자 등록
     * 
     * @param person 사용자 정보
     * @return 사용자 등록 정보
     * 
     * @author yblee
     * @since 2023.04.10
     */
    @PostMapping
    public ResponseEntity<PersonDTO> insertPersonInfo(@RequestBody PersonDTO person) {
        if (personServ.insertPersonInfo(person) == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(person);
        }
    }

    /**
     * 사용자 정보 수정
     * 
     * @param id     사용자 id
     * 
     * @param person 사용자 정보 객체
     * @return 사용자 수정 정보
     * 
     * @author yblee
     * @since 2023.04.10
     */
    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> updatePersonInfo(@PathVariable String id,
            @RequestBody PersonDTO person) {
        List<PersonDTO> updatePerson = this.personServ.updatePersonInfo(id, person);

        if (updatePerson == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(updatePerson);
        }
    }

    /**
     * ID와 일치하는 사용자 삭제 및 반환
     * 
     * @param id 사용자 ID
     * @return 삭제된 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.10
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonDTO> deletePersonInfo(@PathVariable String id) {
        PersonDTO person = this.personServ.deletePersonInfo(id);
        if (person == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(person);
        }
    }
}
