package kr.ymtech.ojt.spring.dto;
/**
 * 수정할 사용자 정보
 * 
 * @author yblee
 * @since 2023.04.13
 */
public class UpdatePersonDTO {
    private PersonDTO old;
    private PersonDTO update;
    /** 
     * @return 기존 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.12
     */
    public PersonDTO getOld() {
        return old;
    }
    /**
     * @param old 기존 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.12
     */
    public void setOld(PersonDTO old) {
        this.old = old;
    }
    /**
     * @return 수정한 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.12
     */
    public PersonDTO getUpdate() {
        return update;
    }
    /**
     * @param update 수정할 사용자 정보
     * 
     * @author yblee
     * @since 2023.04.12
     */
    public void setUpdate(PersonDTO update) {
        this.update = update;
    }

}
