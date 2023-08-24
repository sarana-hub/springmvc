package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
//@Controller
//@ResponseBody
@RestController
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {

        response.getWriter().write("ok");
        //서블릿을 직접 다룰 때처럼 HttpServletResponse객체를 통해 HTTP 메시지 바디에 직접 ok 응답 메시지를 전달

    }


    /**
     * HttpEntity, ResponseEntity(Http Status 추가)
     * @return
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {

        return new ResponseEntity<>("ok", HttpStatus.OK);
        //HttpStatus.CREATED로 변경하면 201 응답

    }


    //@ResponseBody   //view를 사용하지 않고, HTTP 메시지 컨버터를 통해서 HTTP 메시지를 직접 입력
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }


    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() { //ResponseEntity를 반환
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
        //HTTP 메시지 컨버터를 통해서 JSON 형식으로 변환되어서 반환
    }


    //@ResponseBody   //@ResponseBody를 사용하는데, HTTP 응답 코드 설정하려면?
    @ResponseStatus(HttpStatus.OK)  //응답 코드 설정   //동적으로 변경하려면 ResponseEntity를 사용하면 된다.
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }

}
