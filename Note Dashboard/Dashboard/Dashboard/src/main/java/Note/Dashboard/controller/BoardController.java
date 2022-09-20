package Note.Dashboard.controller;

import Note.Dashboard.entity.Board;
import Note.Dashboard.entity.CategoryType;
import Note.Dashboard.entity.Member;
import Note.Dashboard.entity.OrderSearch;
import Note.Dashboard.repository.BoardRepository;
import Note.Dashboard.repository.MemberRepository;
import Note.Dashboard.service.BoardService;
import lombok.Getter;
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
    public String boardBusiness(@PathVariable("memberId") Long memberId, @ModelAttribute Board board, Model model) {
        List<Board> findByBusiness = boardRepository.findByCategory(CategoryType.BUSINESS);
        String type = CategoryType.BUSINESS.getDisplayValue();
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        model.addAttribute("member", member);
        model.addAttribute("boards", findByBusiness);
        model.addAttribute("type", type);
        return "home";
    }

    @GetMapping("/board/Social/{memberId}")
    public String boardSocial(@ModelAttribute Board board, @PathVariable("memberId") Long memberId, Model model) {
        List<Board> findByBusiness = boardRepository.findByCategory(CategoryType.SOCIAL);
        String type = CategoryType.SOCIAL.getDisplayValue();
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        model.addAttribute("member", member);
        model.addAttribute("boards", findByBusiness);
        model.addAttribute("type", type);
        return "home";
    }

    @GetMapping("/board/Important/{memberId}")
    public String boardImportant(@ModelAttribute Board board, @PathVariable("memberId") Long memberId, Model model) {
        List<Board> findByBusiness = boardRepository.findByCategory(CategoryType.IMPORTANT);
        String type = CategoryType.IMPORTANT.getDisplayValue();
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        model.addAttribute("member", member);
        model.addAttribute("boards", findByBusiness);
        model.addAttribute("type", type);
        return "home";
    }

    @GetMapping("/board/ChangeBusiness/{boardId}")
    public String boardChangeBusiness(@PathVariable("boardId") Long boardId) {
        boardRepository.updateBoardCategoryType(CategoryType.BUSINESS, boardId);

        return "redirect:/";
    }

    @GetMapping("/board/ChangeSocial/{boardId}")
    public String boardChangeSocial(@PathVariable("boardId") Long boardId) {
        boardRepository.updateBoardCategoryType(CategoryType.SOCIAL, boardId);

        return "redirect:/";
    }

    @GetMapping("/board/ChangeImportant/{boardId}")
    public String boardChangeImportant(@PathVariable("boardId") Long boardId) {
        ;
        boardRepository.updateBoardCategoryType(CategoryType.IMPORTANT, boardId);
        return "redirect:/";
    }

    @GetMapping("/board/remove/{boardId}")
    public String remove(@PathVariable("boardId") Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        Board board = findBoard.get();
        boardRepository.delete(board);
        return "redirect:/";
    }

    @GetMapping("/board")
    public String Search(@ModelAttribute("orderSearch") OrderSearch orderSearch, @ModelAttribute Board board, @ModelAttribute Member member, @RequestParam("title") String title, Model model) {
        List<Board> result = boardService.findBoardSearch(title);
        model.addAttribute("boards", result);
        return "home";
    }
}
