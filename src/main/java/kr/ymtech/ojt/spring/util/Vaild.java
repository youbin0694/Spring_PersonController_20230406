package kr.ymtech.ojt.spring.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 정규식 검증 클래스
 * 
 * @author yblee
 * @since 2023.04.10
 */
public class Vaild {
    /**
     * Email 형식에 맞는지 검증하는 함수
     * 
     * @param email 요청한 사용자 Email
     * @return Email이 정규식에 일치하는지
     * 
     * @author yblee
     * @since 2023.04.10
     */
    public boolean isEmail(String email) {
        String regex = "^[_a-zA-Z0-9-]+(.[_a-zA-Z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
