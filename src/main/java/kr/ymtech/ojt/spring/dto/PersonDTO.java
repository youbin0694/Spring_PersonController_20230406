package kr.ymtech.ojt.spring.dto;

/**
 * 사용자 정보
 * 
 * @author yblee
 * @since 2023.04.06
 */
public class PersonDTO {
    private String id;
    private String name;
    private int age;
    private String email;

    /**
     * @return 사용자 ID
     * 
     * @author yblee
     * @since 2023.04.06
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 설정할 사용자 ID
     * 
     * @author yblee
     * @since 2023.04.06
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 사용자 Name
     * 
     * @author yblee
     * @since 2023.04.06
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 설정할 사용자 Name
     * 
     * @author yblee
     * @since 2023.04.06
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 사용자 Age
     * 
     * @author yblee
     * @since 2023.04.06
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age 설정할 사용자 Age
     * 
     * @author yblee
     * @since 2023.04.06
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return 사용자 Email
     * 
     * @author yblee
     * @since 2023.04.06
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email 설정할 사용자 Email
     * 
     * @author yblee
     * @since 2023.04.06
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", age=" + age + ", email=" + email;
    }
}
