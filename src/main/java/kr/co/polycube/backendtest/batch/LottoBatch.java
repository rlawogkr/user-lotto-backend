package kr.co.polycube.backendtest.batch;

import jakarta.transaction.Transactional;
import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.entity.Winner;
import kr.co.polycube.backendtest.repository.LottoRepository;
import kr.co.polycube.backendtest.repository.WinnerRepository;
import kr.co.polycube.backendtest.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LottoBatch {
    private final LottoService lottoService;
    private final LottoRepository lottoRepository;
    private final WinnerRepository winnerRepository;

    @Scheduled(cron = "0 0 0 * * SUN")  // 매주 일요일 0시에 실행
    @Transactional // 트랜잭션 처리. 추가한 부분
    public void checkWinners() {
        List<Integer> winningNumbers = lottoService.makeWinningNumbers();
        List<Lotto> allLottos = lottoRepository.findAll();

        for (Lotto lotto : allLottos) {
            int rank = lottoService.checkRank(lotto.getNumbers(), winningNumbers);
            if (rank > 0) {
                Winner winner = new Winner(lotto.getId(), rank);
                winnerRepository.save(winner);
            }
        }
    }
}
