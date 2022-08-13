package Note.Dashboard.controller;

import Note.Dashboard.entity.Member;
import Note.Dashboard.repository.MemberRepository;
import Note.Dashboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {

        return "member/pages-sign-in";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "member/pages-sign-in";
        }
        Member loginMember = memberService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "member/pages-sign-in";
        }
        //세션을 가져옴
        HttpSession session = request.getSession();
        //세션에 로그인 세션 정보를 보관
        session.setAttribute("loginMember", loginMember);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String postLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/member/add")
    public String addForm(@ModelAttribute Member member) {
        return "member/pages-sign-up";
    }

    @PostMapping("/member/add")
    public String add(@Valid @ModelAttribute Member member,BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "member/pages-sign-up";
        }
        try {
            memberService.join(member);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            return "member/pages-sign-up";
        }

        return "redirect:/login";
    }
}
