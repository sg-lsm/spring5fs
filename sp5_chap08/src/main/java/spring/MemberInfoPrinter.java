package spring;

public class MemberInfoPrinter {
    private MemberDao memberDao;
    private MemberPrinter printer;

    public void memberInfoPrinter(String email){
        Member member = memberDao.searchByEmail(email);
        if(email == null){
            System.out.println("이메일이 존재하지 않습니다.");
        }
        printer.print(member);
        System.out.println();
    }
    public void setMemberDao (MemberDao memberDao){
        this.memberDao = memberDao;
    }
    public void setPrinter(MemberPrinter printer){
        this.printer = printer;
    }
}
