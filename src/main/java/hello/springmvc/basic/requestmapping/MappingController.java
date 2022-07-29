package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 기본 요청
     * HTTP 메서드 지정안하면, 모두 허용 (GET, HEAD, POST, PUT, PATCH, DELETE)
     */
    @RequestMapping("/hello-basic")     // "/hello-basic", "/hello-basic/" 둘다 허용
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * method 특정 HTTP 메서드 요청만 허용
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * HTTP 메서드 매핑 축약 애노테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }


    /**
     * PathVariable(경로 변수) 사용
     * 변수명(@PathVariable의 이름과 파라미터 이름)이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     */
    @GetMapping("/mapping/{userId}")    //템플릿화 //리소스 경로에 식별자(파라미터)를 넣음
    /*public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }*/
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    /**
     * PathVariable 사용 -다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }


    /**
     * 특정 파라미터 조건 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
    */
    //잘 안씀
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더 조건 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    //잘 안씀
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * 미디어 타입 조건 매핑 - HTTP 요청 Content-Type
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    //@PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }
    /**
     * 미디어 타입 조건 매핑 - HTTP 요청 Accept
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    //@PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
