package controller;


// 비밀번호 변경을 위해 현재 비밀번호와 바꿀 비밀번호를 저장한다. 또한 이것이 유효한지 테스트하기 위해 validator를 작성해 테스트한다.
public class ChangePwdCommand {
    private String currentPassword;
    private String newPassword;

    public String getCurrentPassword(){
        return currentPassword;
    }
    public String getNewPassword(){
        return newPassword;
    }
    public void setCurrentPassword(String currentPassword){
        this.currentPassword = currentPassword;
    }
    public void setNewPassword(String newPassword){
        this.newPassword = newPassword;
    }
}
