package kr.co.polycube.backendtest.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class LottoService {
    // 당첨 번호 생성
    public List<Integer> makeWinningNumbers() {
        return new Random().ints(1, 46)
                .distinct()
                .limit(6)
                .boxed()
                .collect(Collectors.toList());
    }

    // 등수 판별 로직
    public int checkRank(List<Integer> userNumbers, List<Integer> winningNumbers) {
        int matchCount = 0;
        for (Integer userNumber : userNumbers) {
            if (winningNumbers.contains(userNumber)) {
                matchCount++;
            }
        }

        switch (matchCount) {
            case 6:
                return 1; // 1등
            case 5:
                return 2; // 2등
            case 4:
                return 3; // 3등
            case 3:
                return 4; // 4등
            case 2:
                return 5; // 5등
            default:
                return 0; // 꽝
        }
    }
}
