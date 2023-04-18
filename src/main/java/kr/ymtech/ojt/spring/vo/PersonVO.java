package kr.ymtech.ojt.spring.vo;

/**
 * 사용자 정보
 * 
 * @author yblee
 * @since 2023.04.13
 */
public class PersonVO {
    private String id;
    private String name;
    private int age;
    private String email;

    /**
     * @return 사용자 ID
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 사용자 ID
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 사용자 Name
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public String getName() {
        return name;
    }

    /**
     * @param id 사용자 Name
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 사용자 Age
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public int getAge() {
        return age;
    }

    /**
     * @param id 사용자 Age
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return 사용자 Email
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param id 사용자 Email
     * 
     * @author yblee
     * @since 2023.04.13
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
