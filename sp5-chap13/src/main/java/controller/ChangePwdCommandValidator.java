package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

// 비밀번호 변경 전 유효성 검사를 위해 작성한 validator이다.
// A.class.isAssignableFrom(a) => 특정 클래스가 어떤 클래스/인터페이스를 상속했는지 알려준다.
public class ChangePwdCommandValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz){
        return ChangePwdCommand.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
        ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
    }
}
