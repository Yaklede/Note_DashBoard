package Note.Dashboard.repository;

import Note.Dashboard.entity.Board;
import Note.Dashboard.entity.CategoryType;
import Note.Dashboard.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BoardRepositoryTest {
    @Autowired MemberRepository memberRepository;
    @Autowired BoardRepository boardRepository;

    @Test
    public void findByCategory(Pageable pageable) throws Exception {
        //given
        Member member = new Member("test", "test", "testA");
        memberRepository.save(member);
        Board board = new Board("title", "content",CategoryType.BUSINESS, member);
        boardRepository.save(board);
        //when
        boardRepository.findByCategory(CategoryType.BUSINESS, pageable);


        }
}