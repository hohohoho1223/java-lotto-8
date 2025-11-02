package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoNumberGenerator;
import lotto.view.InputView;

public class LottoController {
    private final LottoNumberGenerator generator = new LottoNumberGenerator();

    public void run(){
        int purchaseAmount = InputView.inputPurchaseAmount();
        int count = calculateLottoCount(purchaseAmount);

        System.out.println(count+"개를 구매했습니다.");

        for (int i = 0; i < count; i++) {
            Lotto lotto = generator.Generator();
            System.out.println(lotto.getNumbers());
        }
    }

    public int calculateLottoCount(int amount) {

        int lottoCount = amount/1000;
        return lottoCount;
    }
}
