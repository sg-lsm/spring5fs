package spring;


// 이메일과 비밀번호가 일치하는지 확인 후 AuthInfo 객체를 생성하는 클래스이다.
public class AuthService {
    private MemberDao memberDao;

    public void setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public AuthInfo authenticate(String email, String password){
        Member member = memberDao.selectByEmail(email);
        if(member == null){
            throw new WrongIdPasswordException();
        }
        if(!member.matchPassword(password)){
            throw new WrongIdPasswordException();
        }
        return new AuthInfo(member.getId(),member.getEmail(),member.getName());
    }
}
