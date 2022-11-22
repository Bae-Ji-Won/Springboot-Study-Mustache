package com.mustache.bbs.Domain.Dto.User;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class UserResponse {
    private long id;
    private String username;

    private String message;

}
