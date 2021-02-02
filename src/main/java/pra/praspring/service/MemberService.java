package pra.praspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pra.praspring.domain.Member;
import pra.praspring.repository.MemberRepository;
import pra.praspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {

        // 같은 이름이 있는 중복 회원은 안된다, 만들어서 메소드화 하기(control + T)
        validateDuplicateMember(member);
//        Optional 붙여서 쓰면 이렇게 써도 됨
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("Already Exist Name");
//                });
        memberRepository.save(member);
        return member.getId();
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("Already exist Name");
        });
    }
}
