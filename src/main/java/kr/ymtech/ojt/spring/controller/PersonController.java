package kr.ymtech.ojt.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ymtech.ojt.spring.dto.PersonDTO;
import kr.ymtech.ojt.spring.dto.UpdatePersonDTO;
import kr.ymtech.ojt.spring.service.IPersonService;
import kr.ymtech.ojt.spring.util.Vaild;
import kr.ymtech.ojt.spring.vo.PersonVO;

/**
 * 사용자 기능 관리
 * 
 * @author yblee
 * @since 2023.04.06
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    @Qualifier("personService")
    private IPersonService personServ;
    private PersonVO personVO = new PersonVO();

    /**
     * ID와 일치하는 사용자 반환
     * 
     * @param id 요청한 사용한 ID
     * @return ID와 일치하는 사용자
     * 
     * @author yblee
     * @since 2023.04.06
     */
    @GetMapping("/byid/{id}")
    public ResponseEntity<PersonVO> findPersonById(@PathVariable String id) {
        this.personVO = personServ.findPersonById(id);
        if (personVO == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(this.personVO);
        }
    }

    /**
     * 전체 사용자 반환
     * 
     * @return 전체 사용자
     * 
     * @author yblee
     * @since 2023.04.14
     */
    @GetMapping("/by/all")
    public ResponseEntity<List<PersonVO>> findPersonAll() {
        List<PersonVO> personAllList = personServ.findPersonAll();
        if (this.personVO == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(personAllList);
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
    @GetMapping("/byemail/{email}")
    public ResponseEntity<PersonVO> findPersonByEmail(@PathVariable String email) {
        Vaild vaild = new Vaild();

        if (vaild.isEmail(email)) {
            this.personVO = personServ.findPersonByEmail(email);
        }

        if (this.personVO == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(this.personVO);
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
        if (personServ.insertPersonInfo(person) == false) {
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
    public ResponseEntity<UpdatePersonDTO> updatePersonInfo(@PathVariable String id,
            @RequestBody PersonDTO person) {
        UpdatePersonDTO updatePerson = personServ.updatePersonInfoSet(id, person);

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
    public ResponseEntity<Boolean> deletePersonInfo(@PathVariable String id) {
        boolean flag = personServ.deletePersonInfo(id);
        if (flag == false) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(flag);
        }
    }
}
