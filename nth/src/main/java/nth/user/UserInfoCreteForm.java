package nth.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoCreteForm {

    @Size(min = 3, max=25)
    @NotEmpty(message = "사용자 ID는 필수 항목입니다.")
    private String username;

    @NotEmpty(message = "사용자 닉네임은 필수 항목입니다.")
    private String nickname;

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호2는 필수 항목입니다.")
    private String password2;

    @NotEmpty(message = "이메일 필수 항목입니다.")
    private String email;

}
