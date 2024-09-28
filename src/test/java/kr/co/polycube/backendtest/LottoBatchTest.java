package kr.co.polycube.backendtest;

import kr.co.polycube.backendtest.batch.LottoBatch;
import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.entity.Winner;
import kr.co.polycube.backendtest.repository.LottoRepository;
import kr.co.polycube.backendtest.repository.WinnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LottoBatchTest {
    @Autowired
    private LottoRepository lottoRepository;

    @Autowired
    private WinnerRepository winnerRepository;

    @Autowired
    private LottoBatch lottoBatch;

    @BeforeEach
    public void setup() {
        // 로또 데이터 초기화
        List<Lotto> lottos = Arrays.asList(
                new Lotto(Arrays.asList(7, 28, 33, 2, 45, 19)),
                new Lotto(Arrays.asList(26, 14, 41, 3, 22, 35)),
                new Lotto(Arrays.asList(15, 29, 38, 6, 44, 21)),
                new Lotto(Arrays.asList(4,31,16,42,9,23,36)),
                new Lotto(Arrays.asList(5,17,30,39,10,45,24))
        );
        lottoRepository.saveAll(lottos);
    }

    @Test
    public void testCheckWinners() {
        // 배치 작업을 수동으로 실행
        lottoBatch.checkWinners();

        // 당첨자 저장소에 당첨자가 있는지 확인
        List<Winner> winners = winnerRepository.findAll();
        assertTrue(winners.size() > 0, "당첨자가 존재해야 합니다.");
    }

}
