package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.LottoResult;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private final LottoNumberGenerator generator = new LottoNumberGenerator();

    public void run() {
        try {
            int purchaseAmount = InputView.inputPurchaseAmount();
            int count = calculateLottoCount(purchaseAmount);

            List<Integer> lottoNumbers = InputView.inputLottoNumber();
            int bonusNumber = InputView.inputBonusNumber(lottoNumbers);
            List<Lotto> lottos = generateLottos(count);

            // 예: 로또가 아예 생성되지 않은 경우
            if (lottos.isEmpty()) {
                throw new IllegalStateException("[ERROR] 발행된 로또가 없습니다.");
            }

            LottoResult result = new LottoResult(lottos, lottoNumbers, bonusNumber);
            OutputView.printResult(result);

        } catch (IllegalStateException e) {
            // 프로그램 로직 상태 오류 처리
            System.out.println(e.getMessage());
        }
    }

    private List<Lotto> generateLottos(int count) {
        System.out.println(count+"개를 구매했습니다.");
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Lotto lotto = generator.generate();
            System.out.println(lotto.getNumbers());
            lottos.add(lotto);
        }
        return lottos;
    }

    public int calculateLottoCount(int amount) {
        int lottoCount = amount/1000;
        return lottoCount;
    }
}
