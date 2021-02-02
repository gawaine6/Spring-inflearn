package pra.praspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

//    정적 컨텐츠 : 그냥 파일을 그대로 내려준다
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

//    MVC와 템플릿 엔진 : 템플릿 엔진을 MVC방식으로 쪼개서 View를 템플릿 엔진을 사용해 html을 프로그래밍하고 렌더링해서 렌더링된 html을 클라이언트에 전달
    @GetMapping("hello-string")
    @ResponseBody
//    http의 body부분에 데이터를 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

//    API 방식 : 객체를 반환하는 방식, 컨버터를 사용해서 JSON 스타일로, View 이런거 없이 바로 httpResponse에 값을 넣어서 반
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

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
