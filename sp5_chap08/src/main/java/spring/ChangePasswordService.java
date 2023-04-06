package spring;

import exception.MemberNotFoundException;

public class ChangePasswordService {
    private MemberDao memberDao;

    public void changePassword(String email, String oldPassword, String newPassword) {
        Member member = memberDao.searchByEmail(email);
        if (email == null) {
            throw new MemberNotFoundException();
        }
        member.passwordChange(oldPassword, newPassword);
        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}

