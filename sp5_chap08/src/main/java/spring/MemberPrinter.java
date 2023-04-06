package spring;

public class MemberPrinter {
    public void print(Member member){
        System.out.printf(
                "출력정보:: 아이디:%d, 이메일:%s, 이름:%s, 날짜:%tF\n",
                member.getId() ,member.getEmail(), member.getName(), member.getRegDateTime()
        );
    }
}
