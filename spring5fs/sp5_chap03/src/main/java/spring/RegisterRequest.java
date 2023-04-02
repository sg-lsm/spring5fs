package spring;

import java.time.LocalDateTime;

public class RegisterRequest {
    private String email;
    private String password;
    private String confirmedPassword;
    private String name;

    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail() {
        return email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setConfirmedPassword(String confirmedPassword){
        this.confirmedPassword = confirmedPassword;
    }

    public String getConfirmedPassword(){
        return confirmedPassword;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean isPasswordEqualToConfirmedPassword(){
        return password.equals(confirmedPassword);
    }

}
