package kr.co.polycube.backendtest.controller;

import kr.co.polycube.backendtest.dto.LottoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lottos")
public class LottoController {
    @PostMapping
    public ResponseEntity<LottoResponseDto> makeLottoNumbers() {
        // 로또 번호는 1~45 사이의 중복 없는 6개의 번호로 구성
        List<Integer> numbers = new Random().ints(1, 46)
                .distinct()
                .limit(6)
                .boxed()
                .collect(Collectors.toList());
        // System.out.println(numbers);
        LottoResponseDto response = new LottoResponseDto(numbers);
        return ResponseEntity.ok(response);
    }

}
