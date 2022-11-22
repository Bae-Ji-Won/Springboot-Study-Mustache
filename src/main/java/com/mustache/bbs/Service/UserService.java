package com.mustache.bbs.Service;

import com.mustache.bbs.Domain.Dto.User.UserRequest;
import com.mustache.bbs.Domain.Dto.User.UserResponse;
import com.mustache.bbs.Domain.User;
import com.mustache.bbs.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserResponse findbyid(long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            return new UserResponse(id,"","해당 id의 유저가 없습니다.");
        }else {
            User user = userOptional.get();
            UserResponse userResponse = user.of();
            return userResponse;
        }
    }

    public UserResponse save(UserRequest userRequest){
        User user = userRequest.toEntity();
        Optional<User> namelist = userRepository.findByUserName(user.getUserName());    // 입력받은 이름을 통해 해당하는 데이터 출력함

        log.info("namelist 값 :"+namelist.toString());

        if(namelist.isEmpty()){
            User saveuser = userRepository.save(user);  // id는 DB에서 가져와서 저장하는거기 때문에 Save 데이터를 호출함
            UserResponse userResponse = UserResponse.builder()       // DB에서 가져온 데이터를 Response DTO에 저장함
                    .id(saveuser.getId())
                    .username(saveuser.getUserName())
                    .message("가입이 완료 되었습니다.")
                    .build();

            return userResponse;
        }else{
            User finduser = namelist.get();
            UserResponse userResponse = new UserResponse();
            userResponse = finduser.of();
            userResponse.setMessage("해당 id는 중복입니다.");
            return userResponse;
        }
    }
}



