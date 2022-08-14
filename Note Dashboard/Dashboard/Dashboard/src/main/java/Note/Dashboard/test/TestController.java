package Note.Dashboard.test;

import Note.Dashboard.dto.BoardDto;
import Note.Dashboard.entity.Board;
import Note.Dashboard.entity.CategoryType;
import Note.Dashboard.entity.Member;
import Note.Dashboard.repository.BoardRepository;
import Note.Dashboard.repository.MemberRepository;
import Note.Dashboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    //@GetMapping("/test")
    public String findByCategory(@PageableDefault(size = 5)Pageable pageable, Model model) {
        Member member = new Member("test", "test", "testA");
        memberRepository.save(member);
        Board board = new Board("title", "content", CategoryType.BUSINESS);
        boardRepository.save(board);

        Page<Board> page = boardRepository.findByCategoryPage(CategoryType.BUSINESS, pageable);
        Page<BoardDto> map = page.map(board1 -> new BoardDto(board1.getTitle(), board1.getContent(),board1.getCategoryType(),board1.getCreatedDate()));
        List<BoardDto> boards = map.getContent();
        int totalPages = map.getTotalPages();
        model.addAttribute("boards", boards);
        model.addAttribute("pages", totalPages);

        return "test";
    }

    //@PostConstruct
    public void init() {

        for (int i = 0; i < 30; i++) {
            boardRepository.save(new Board("title" + i, "content" + i, CategoryType.BUSINESS));
        }
    }


}
