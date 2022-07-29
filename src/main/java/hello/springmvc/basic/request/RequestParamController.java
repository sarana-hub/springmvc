package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    /**
     * 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view 조회X
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }


    /**
     * @RequestParam 사용 - 파라미터 이름으로 바인딩
     * @ResponseBody 추가 - View 조회를 무시하고, HTTP message body에 !직접 해당 내용 입력!
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            //@RequestParam("username") String memberName,
            //@RequestParam("age") int memberAge
            /**HTTP 파라미터 이름과 변수 이름 같으면, @RequestParam(name="xx") !생략 가능!*/
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }
    /**
     * String, int 등의 단순 타입이면  @RequestParam 도 생략 가능!
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }


    /**
     * @RequestParam.required   //파라미터 필수 여부
     * /request-param -> 필수인 username이 없으므로 예외(400에러)
     *
     * 주의! - 파라미터 이름만 사용
     * /request-param?username= -> 빈문자로 통과
     *
     * 주의! - 기본형(primitive)에 null 입력
     * /request-param
     * null을 int에 입력하는 것은 불가능!,
     * 따라서 null을 받을수있는 Integer로 변경해야 함 (또는 defaultValue 사용)
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam - defaultValue 사용
     * 파라미터에 값이 없는 경우 defaultValue 를 사용하면 기본 값을 적용할 수 있다
     * 참고: defaultValue는 빈 문자의 경우에도 설정한 기본 값이 적용
     * /request-param?username=
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 파라미터를 Map으로 조회
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...] ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }




    /**
     * @ModelAttribute 사용 ->HelloData 객체가 생성되고, 요청 파라미터의 값도 모두 들어가 있다.
     * 참고: model.addAttribute(helloData) 코드도 함께 자동 적용됨
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * @ModelAttribute 생략 가능!
     * String, int 같은 단순 타입 = @RequestParam 사용
     * 그외 나머지(argument resolver로 지정해둔 타입 외) = @ModelAttribute 사용
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
