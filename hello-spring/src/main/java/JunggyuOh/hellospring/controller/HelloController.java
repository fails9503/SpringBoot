package JunggyuOh.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // 맵핑을 통해서 Get방식으로 /hello라는 url이 입력되면
    // Spring컨테이너에서 model을 생성해서 삽입해준다
    // 삽입된 model에 속성명 : data 속성값 : hello!!를 입력시켜준다
    // return으로 주는 String은 내가 찾아갈 html페이지의 이름과 똑같다
    // Spring은 기본적으로 templates 밑에 있는 파일을 찾아 렌더링한다
    // 여기서는 hello.html로 가는 것이다.

    // 컨트롤러에서 리턴값으로 문자를 반환하면 뷰 리졸버가 화면ㅇ르 찾아서 처리한다
    // 스프링 부트 템플릿엔진 기본 viewName매핑
    // resources:templates/ +{ViewName}+ .html

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

}
