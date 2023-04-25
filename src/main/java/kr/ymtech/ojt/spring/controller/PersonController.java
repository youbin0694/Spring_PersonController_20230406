package kr.ymtech.ojt.spring.controller;

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

import io.github.mjyoun.core.data.Result;
import kr.ymtech.ojt.spring.dto.PersonDTO;
import kr.ymtech.ojt.spring.service.IPersonService;
import kr.ymtech.ojt.spring.service.impl.PersonService;
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

    
    private final IPersonService personServ;

    /**
     * (non-javadoc)
     * 
     * @param personServ 
     * 
     * @author yblee
     * @since 2023.04.25
     */
    public PersonController(@Qualifier(PersonService.QUALIFIER_BEAN) IPersonService personServ){
        this.personServ = personServ;
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
    @GetMapping("/byid/{id}")
    public ResponseEntity<Result<PersonVO>> findPersonById(@PathVariable String id) {
        return ResponseEntity.ok(this.personServ.findPersonById(id));
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
    public ResponseEntity<Result<PersonVO>> findPersonAll() {
        return ResponseEntity.ok(this.personServ.findPersonAll());
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
    public ResponseEntity<Result<PersonVO>> findPersonByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.personServ.findPersonByEmail(email));
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
    public ResponseEntity<Result<Boolean>> insertPersonInfo(@RequestBody PersonDTO person) {
        return ResponseEntity.ok(this.personServ.insertPersonInfo(person));
    }

    /**
     * 사용자 정보 수정
     * 
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
    public ResponseEntity<Result<Boolean>> updatePersonInfo(@PathVariable String id,
            @RequestBody PersonDTO person) {
        return ResponseEntity.ok(this.personServ.updatePersonInfoSet(id, person));
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
    public ResponseEntity<Result<Boolean>> deletePersonInfo(@PathVariable String id) {
        return ResponseEntity.ok(this.personServ.deletePersonInfo(id));
    }
}
