package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestMemberController {
    private MemberDao memberDao;
    private MemberRegisterService registerService;

    @GetMapping("/api/members")
    public List<Member> members(){
        return memberDao.selectAll();
    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<Object> member(@PathVariable Long id, HttpServletResponse res) throws IOException {
        Member member = memberDao.selectById(id);
        if(member == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @PostMapping("/api/members")
    public ResponseEntity<Object> newMember(
            @RequestBody @Valid RegisterRequest regReq, Errors errors, HttpServletResponse res
            ) throws IOException{
        try {
            new RegisterRequestValidator().validate(regReq, errors);
            if(errors.hasErrors()){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("bad request"));
                String errorCodes = errors.getAllErrors().stream().map(err -> err.getCodes()[0]).collect(Collectors.joining(","));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes : " + errorCodes));
            }
            Long newMemberId = registerService.regist(regReq);
//            res.setHeader("Location", "/api/members/" + newMemberId);
//            res.setStatus(HttpServletResponse.SC_CREATED);
            URI uri = URI.create("/api/members/" + newMemberId);
            return ResponseEntity.created(uri).build();
        }catch (DuplicateMemberException ex){
//            res.sendError(HttpServletResponse.SC_CONFLICT);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    public void setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    public void setRegisterService(MemberRegisterService registerService){
        this.registerService = registerService;
    }
}
