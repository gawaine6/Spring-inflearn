package pra.praspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pra.praspring.repository.*;
import pra.praspring.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 자바 코드로 직접 스프링 빈 등록하는 방식
// 먼저 MemberService에 @Service, @Autowired 지우기
// 다음 MemoryMemberRepository에 @Repository 지우기
// MemberController는 그대로 둔다

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
//        스프링이 뜰 때 Configuration을 읽고 memberService를 Bean에 등록한다
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new JdbcMemberRepository(dataSource);
//        return new MemoryMemberRepository();
//        서비스, 레포지트리를 다 스프링 빈에 등록하고 스프링 빈에 등록된 멤버레포지트리를 멤버서비스에 넣어준다

//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

}
