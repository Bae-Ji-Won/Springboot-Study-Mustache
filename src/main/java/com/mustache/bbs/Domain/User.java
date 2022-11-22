package com.mustache.bbs.Domain;

import com.mustache.bbs.Domain.Dto.User.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String passWord;

    // password는 노출이 되면 안되므로 제외함
    public UserResponse of(){
        UserResponse user = UserResponse.builder()
                .id(this.id)
                .username(this.userName)
                .build();

        return user;
    }
}
