package Note.Dashboard.service;

import Note.Dashboard.entity.Member;
import Note.Dashboard.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    public void 중복체크() throws Exception {
        //given
        Member member = new Member("test", "test", "test");
        memberService.join(member);

        //when
        Member memberA = new Member("test", "test", "test");

        //then
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> memberService.join(memberA));

        assertEquals("이미 존재하는 회원입니다.", illegalArgumentException.getMessage());


    }
}