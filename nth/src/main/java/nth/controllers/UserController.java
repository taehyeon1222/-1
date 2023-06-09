package nth.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nth.user.UserInfo;
import nth.user.UserInfoCreteForm;
import nth.user.UserInfoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserInfoService userInfoService;

    @GetMapping("/user/signup")
    public String signup(UserInfoCreteForm userInfoCreteForm, Principal principal){
        //UserInfo userInfo = this.userInfoService.(principal.getName());
        return "user/signup_form";
    }


    @PostMapping("/user/signup")
    public String signup(@Valid UserInfoCreteForm userCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user/signup_form";
        }
        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password1","passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "user/signup_form";
        }


        try{
        userInfoService.create(userCreateForm.getUsername(),
                userCreateForm.getPassword1(), userCreateForm.getEmail()
        ,userCreateForm.getNickname());
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.rejectValue("username",
                    "signupFailed","이미 등록된 사용자 입니다.");
            return "user/signup_form";
        }

//        userInfoService.create(userCreateForm.getUsername(),
//                userCreateForm.getPassword1(), userCreateForm.getEmail()
//                ,userCreateForm.getNickname());
        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String login(){
        return "user/login_form";
    }

}
