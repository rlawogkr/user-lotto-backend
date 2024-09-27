package kr.co.polycube.backendtest.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
}
