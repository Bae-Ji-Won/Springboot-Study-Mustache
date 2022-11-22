package com.mustache.bbs.Domain.Dto.User;

import com.mustache.bbs.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
// 사용자에게 입력받은 데이터를 받아 저장하는 곳
public class UserRequest {
    private String username;
    private String password;

    
    // Request DTO를 Entity로 보냄
    public User toEntity(){
        User user = User.builder()
                .userName(this.username)
                .passWord(this.password)
                .build();

        return user;
    }
}
