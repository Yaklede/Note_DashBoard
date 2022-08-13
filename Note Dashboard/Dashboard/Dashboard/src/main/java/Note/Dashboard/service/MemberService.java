package Note.Dashboard.service;

import Note.Dashboard.entity.Member;
import Note.Dashboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //로그인
    //repository에서 회원 정보를 찾고 아이디 비밀번호가 맞으면 멤버 반환
    public Member login(String loginId, String password) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        return findMember.filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    //중복 검증 로직
    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByLoginId(member.getLoginId());
        if(!findMember.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

}
