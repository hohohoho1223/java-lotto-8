package lotto.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoControllerTest {
    LottoController controller = new LottoController();

    @DisplayName("입력한 금액에 따른 로또 티켓 개수가 반환되어야 한다.")
    @Test
    void 입력한_금액에_따른_로또_개수_검증() {
        int amount = 8000;
        int count = controller.calculateLottoCount(amount);

        assertEquals(8, count);
    }



}
