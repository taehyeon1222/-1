package nth.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nth.answer.AnswerForm;
import nth.answer.AnswerService;
import nth.post.Post;
import nth.post.PostService;
import nth.user.UserInfo;
import nth.user.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AnswerController {

    private final PostService postService;
    private final AnswerService answerService;
    private final UserInfoService userInfoService;

    //작성자 저장
    @PostMapping("/post/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") long id,
                               @Valid AnswerForm answerForm, BindingResult
                                           bindingResult, Principal principal,
                               HttpServletRequest request){
        UserInfo userInfo = this.userInfoService.getUser(principal.getName());
        Post post = this.postService.getPost(id);

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if(bindingResult.hasErrors()){
            model.addAttribute("post", post);
            return "post/list_d";
        }
        if(principal == null){
            System.out.println("널");
            return "post/list";

        }

        //this.answerService.create(post, answerForm.getContent());
        this.answerService.create(post,answerForm.getContent(),userInfo);
            return String.format("redirect:/post/detail/%s",id);
    }


    }


