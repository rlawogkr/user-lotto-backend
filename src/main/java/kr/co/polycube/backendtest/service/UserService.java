package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.Dto.UserRequestDto;
import kr.co.polycube.backendtest.Dto.UserResponseDto;
import kr.co.polycube.backendtest.entity.Users;
import kr.co.polycube.backendtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long save(UserRequestDto dto){
        Users user = new Users(dto.getName());
        return userRepository.save(user).getId();
    }

    public UserResponseDto findById(Long id){
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return new UserResponseDto(user.getId(), user.getName());
    }

    public UserResponseDto update(Long id, UserRequestDto dto){
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        user.setName(dto.getName());
        userRepository.save(user);
        return new UserResponseDto(user.getId(), user.getName());
    }


}
