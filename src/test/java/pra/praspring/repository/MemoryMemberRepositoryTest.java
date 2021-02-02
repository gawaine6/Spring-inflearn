package pra.praspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pra.praspring.domain.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

//    테스트할 때, 메소드의 테스트 순서는 보장되지 않는다. 그렇다고 해서 순서에 의존되게 만들어도 안된다
//    각각 돌려보면 잘 돌아가지만 전체를 돌려보면 오류가 뜬다, 왜 그럴까
//    한쪽 메소드에서 생성된 객체가 다른 메소드에서 다시 생성된다. 그래서 서로 다른 객체가 생성된 셈이다
//    근데 와중에 같은 이름으로 돌려버리니까 객체값이 달라서 오류가 뜨는거다~~ 이마리야
//    그래서 각 메소드 실행이 끝나면 데이터를 깔끔하게 해줘야 한다 이마리야, 고거이 AfterEach
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        내가 저장한 member랑 repository에서 갖고온 member랑 똑같은지 테스트
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
//        이름으로 찾기
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
