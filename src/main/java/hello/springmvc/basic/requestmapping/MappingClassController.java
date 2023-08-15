package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/map/users")   //클래스 레벨에 매핑 정보-> 메서드 레벨에서 해당 정보를 조합해서 사용
public class MappingClassController {

    /**
     * GET /map/users
     */
    @GetMapping
    public String users() {
        return "get users";
    }

    /**
     * POST /map/users
     */
    @PostMapping
    public String addUser() {
        return "post user";
    }

    /**
    * GET /map/users/{userId}
     */
    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get userId=" + userId;
    }

    /**
     * PATCH /map/users/{userId}
     */
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId=" + userId;
    }

    /**
     * DELETE /map/users/{userId}
     */
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId=" + userId;
    }

}
