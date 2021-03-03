package JunggyuOh.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    
    //GetMapping을 통해서 "localhost:8080/" 다음에 오는 String값에 따라 컨트롤러 매핑
    
    // 맵핑을 통해서 Get방식으로 /hello라는 url이 입력되면
    // Spring컨테이너에서 model을 생성해서 삽입해준다
    // 삽입된 model에 속성명 : data 속성값 : hello!!를 입력시켜준다
    // return으로 주는 String은 내가 찾아갈 html페이지의 이름과 똑같다
    // Spring은 기본적으로 templates 밑에 있는 파일을 찾아 렌더링한다
    // 여기서는 hello.html로 가는 것이다.

    // 컨트롤러에서 리턴값으로 문자를 반환하면 뷰 리졸버가 화면을 찾아서 처리한다
    // 스프링 부트 템플릿엔진 기본 viewName매핑
    // resources:templates/ +{ViewName}+ .html

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    // 컨트롤+p하면 파라미터 정보를 볼 수 있음
    // required=true가 기본값임 반드시 입력해야 하는지를 지정하는값
    // 파라미터 넘길때 required가 true인 값을 같이 넘겨주지 않으면 오류가 발생함
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name="name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // ResponseBody는 http의 body에 return의 데이터를 직접 반환해줌
    // ResponseBody를 사용하면 뷰 리졸버를 사용하지 않음
    // 뷰같은게 필요 없음 그냥 그대로 내려감
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(name="name", required = false) String name) {

//        if(name==null) {
//            return "hello";
//        }
        return "hello " + name;
    }

    // ResponseBody의 return 값이 문자일경우에는 그냥 넘김
    // 하지만 객체일 경우에는 json방식으로 데이터를 만들어서 반환
    // 이경우 hello객체를 보고 HttpMessageConverter가 동작
    // 단순 문자면 StringConverter
    // 객체면 JsonConverter이 동작
    // Spring은 Jackson이란 라이브러리를 기본으로 사용함(객체를 json으로 바구는 라이브러리)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName("hello");
        return hello;
    }

    // getter setter 자바 bean 표준방식
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }




}
