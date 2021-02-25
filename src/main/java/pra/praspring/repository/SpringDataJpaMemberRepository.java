package pra.praspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pra.praspring.domain.Member;

import java.util.Optional;


public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
