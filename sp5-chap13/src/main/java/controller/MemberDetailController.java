package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.Member;
import spring.MemberDao;
import spring.MemberNotFoundException;

@Controller
public class MemberDetailController {
    private MemberDao memberDao;

    public void setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    @GetMapping("/member/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        Member member = memberDao.selectById(id);
        if(member == null){
            throw new MemberNotFoundException();
        }
        model.addAttribute("member", member);
        return "member/memberDetail";
    }

    // 주어진 경로 변수값의 타입이 올바르지 않을 때 발생 => 에러익셉션 대신 handleTypeMismatchException()을 실행
    @ExceptionHandler(TypeNotPresentException.class)
    public String handleTypeMismatchException(){
        return "member/invalidId";
    }
    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException(){
        return "member/noMember";
    }
}
