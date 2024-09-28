package kr.co.polycube.backendtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LottoResponseDto {
    private List<Integer> numbers;
}
