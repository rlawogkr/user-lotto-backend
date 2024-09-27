package kr.co.polycube.backendtest.controller;

import kr.co.polycube.backendtest.Dto.UserRequestDto;
import kr.co.polycube.backendtest.Dto.UserResponseDto;
import kr.co.polycube.backendtest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 2.2 TODO: user 등록 API 구현
    @PostMapping
    public ResponseEntity<Map<String, Long>> createUser(@RequestBody UserRequestDto dto) {
        Long id = userService.save(dto);
        Map<String, Long> response = new HashMap<>();
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    // 2.2 TODO: user 조회 API 구현
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id){
        UserResponseDto response = userService.findById(id);
        return ResponseEntity.ok(response);
    }

    // 2.2 TODO: user 수정 API 구현
    @PutMapping("{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto dto){
        UserResponseDto response = userService.update(id, dto);
        return ResponseEntity.ok(response);
    }

}
