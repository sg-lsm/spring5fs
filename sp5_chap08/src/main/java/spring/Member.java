package spring;

import exception.MemberNotFoundException;

import java.time.LocalDateTime;

public class Member {
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime regDateTime;

    public Member(String email, String password, String name, LocalDateTime regDateTime){
        this.email = email;
        this.password = password;
        this.name = name;
        this.regDateTime = regDateTime;
    }

    void setId(long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }
    public LocalDateTime getRegDateTime(){
        return regDateTime;
    }

    public void passwordChange(String oldPwd, String newPwd){
        if(!password.equals(oldPwd)){
            throw new MemberNotFoundException();
        }
        password = newPwd;
    }
}
