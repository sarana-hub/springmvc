package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController     //반환 값을 HTTP 메시지 바디에 바로 입력 (따라서 실행 결과로 ok 메세지)
public class LogTestController {

    //private final Logger log= LoggerFactory.getLogger(getClass());
    //@Slf4j 가 자동으로 넣어줌

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        //로그 레벨: TRACE > DEBUG > INFO > WARN > ERROR
        // application.properties에서 로그 레벨 설정
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        //log.debug("String concat log=" + name);   이런 방식으로 사용하면 안됨!
        //로그 출력 레벨을 info로 설정해도(로그를 사용하지 않아도) 더하기 연산이 발생! (의미없는 연산)

        return "ok";
    }
}
