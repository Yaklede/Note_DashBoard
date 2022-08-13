package Note.Dashboard.controller;

import Note.Dashboard.dto.BoardDto;
import Note.Dashboard.entity.Board;
import Note.Dashboard.entity.Member;
import Note.Dashboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final BoardRepository boardRepository;

    @GetMapping("/")
    public String home(@SessionAttribute(name = "loginMember", required = false)Member member,@ModelAttribute Board board ,Model model) {
        //세션데이터가 없으면 로그인홈으로

        if (member == null) {
            return "home";
        }//세션데이터가 있으면 노트창으로

        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        model.addAttribute("member", member);
        String type = "a";
        model.addAttribute("type", type);
        return "home";
    }

}
