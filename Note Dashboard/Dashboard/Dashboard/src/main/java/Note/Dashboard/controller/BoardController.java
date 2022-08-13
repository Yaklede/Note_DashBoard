package Note.Dashboard.controller;

import Note.Dashboard.entity.Board;
import Note.Dashboard.entity.CategoryType;
import Note.Dashboard.entity.Member;
import Note.Dashboard.repository.BoardRepository;
import Note.Dashboard.repository.MemberRepository;
import Note.Dashboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/board/add/{memberId}")
    public String boardAdd(@ModelAttribute Board board, @PathVariable("memberId") Long memberId) {


        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        boardService.createBoard(board.getTitle(), board.getContent(), board.getCategoryType(), member);
        return "redirect:/";
    }

    @GetMapping("/board/Business/{memberId}")
    public String boardBusiness(@PathVariable("memberId")Long memberId,@ModelAttribute Board board,Model model) {
        List<Board> findByBusiness = boardRepository.findByCategory(CategoryType.BUSINESS);
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        model.addAttribute("member", member);
        model.addAttribute("boards", findByBusiness);
        String type = "b";
        model.addAttribute("type", type);
        return "home";
    }

    @GetMapping("/board/Social/{memberId}")
    public String boardSocial(@ModelAttribute Board board,@PathVariable("memberId")Long memberId,Model model) {
        List<Board> findByBusiness = boardRepository.findByCategory(CategoryType.SOCIAL);
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        model.addAttribute("member", member);
        model.addAttribute("boards", findByBusiness);
        String type = "s";
        model.addAttribute("type", type);
        return "home";
    }

    @GetMapping("/board/Important/{memberId}")
    public String boardImportant(@ModelAttribute Board board,@PathVariable("memberId")Long memberId,Model model) {
        List<Board> findByBusiness = boardRepository.findByCategory(CategoryType.IMPORTANT);
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        model.addAttribute("member", member);
        model.addAttribute("boards", findByBusiness);
        String type = "i";
        model.addAttribute("type", type);
        return "home";
    }

    @GetMapping("/board/ChangeBusiness/{boardId}")
    public String boardChangeBusiness(@PathVariable("boardId") Long boardId) {

        Optional<Board> findBoard = boardRepository.findById(boardId);
        Board board = findBoard.get();
        boardRepository.updateBoardCategoryType(CategoryType.BUSINESS, boardId);

        log.info("board = {} ", board.getCategoryType());

        return "redirect:/";
    }

    @GetMapping("/board/ChangeSocial/{boardId}")
    public String boardChangeSocial(@PathVariable("boardId")Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        Board board = findBoard.get();
        boardRepository.updateBoardCategoryType(CategoryType.SOCIAL, boardId);

        log.info("board = {} ", board.getCategoryType());

        Optional<Board> findBoard2 = boardRepository.findById(boardId);

        log.info("test = {}", findBoard2.get().getCategoryType());

        return "redirect:/";
    }

    @GetMapping("/board/ChangeImportant/{boardId}")
    public String boardChangeImportant(@PathVariable("boardId")Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        Board board = findBoard.get();
        boardRepository.updateBoardCategoryType(CategoryType.IMPORTANT, boardId);

        log.info("board = {} ", board.getCategoryType());

        return "redirect:/";
    }

    @GetMapping("/board/remove/{boardId}")
    public String remove(@PathVariable("boardId") Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        Board board = findBoard.get();
        boardRepository.delete(board);
        return "redirect:/";
    }


}
