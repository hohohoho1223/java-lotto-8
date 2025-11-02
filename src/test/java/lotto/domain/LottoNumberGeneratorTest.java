package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoNumberGeneratorTest {
    @DisplayName("로또 번호는 6개이며 범위(1~45)내 에서 중복 되지 않아야 한다.")
    @Test
    void 생성된_로또_번호_검증() {
        LottoNumberGenerator generator = new LottoNumberGenerator();
        Lotto lotto = generator.Generator();

        List<Integer> testNumbers = lotto.getNumbers(); // 기존 Lotto 객체 내부의 numbers필드를 참조해서 불러온 것

        assertEquals(6, testNumbers.size());
        assertTrue(testNumbers.stream().allMatch(n -> n >=1 && n <=45));
        assertEquals(testNumbers.stream().distinct().count(), testNumbers.size());
    }

}
