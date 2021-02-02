package pra.praspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pra.praspring.domain.Member;
import pra.praspring.service.MemberService;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
// 멤버서비스를 가져다 써야된다
// 스프링이 관리를 하게 되면 스프링 컨테이너에서 받아쓰도록 변경해줘야된다
// new를 하면 다른 컨트롤러에서도 받아쓸 수 있게 되는데, MemberService에 들어가면 별 기능이 없어서 하나만 생성해서 공용으로 쓰게 해준다
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
//    근데 처음에 쓰니까 에러가 뜬다, memberService가 스프링 빈으로 등록되어있지 않다고 한다
//    처음에 그냥 하면 MemberService는 순수한 자바 클래스라서 스프링이 알 수 있는 방법이 없다 알려줄 방법이 필요해
//    MemberService.java에 @Service라고 넣어준다
//    이렇게 하면 스프링이 올라올 때 스프링 컨테이너에 MemberService를 등록한다
//    Repository는 @Repository 해주면 컨테이너에서 등록함
//    Controller 에서는 Service를 @Autowired, Service는 Repository를 @Autowired 해준다
//    이렇게 해주면 컨트롤러 -> 서비스 -> 레포지트리 의존 관계가 형성된다
//    지금처럼 해준게 Component Scan 방식

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMembrForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
