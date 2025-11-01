package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {
    @DisplayName("문자열 입력값은 예외 발생 - 여러 케이스 검증")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "12a", " ", "!", "천원"})
    void 구입_금액에_입력_값이_문자열_일때(String input) {
        assertThatThrownBy(() -> InputView.parseToInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자");
    }

    @DisplayName("1000원 미만 입력값은 예외 발생 — 여러 케이스 검증")
    @ParameterizedTest
    @ValueSource(ints = {999, 500, 1})
    void 금액이_1000원_미만이면_예외(int input) {
        assertThatThrownBy(() -> InputView.validateAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000원 이상");
    }

    @DisplayName("1000원 단위가 아닌 입력값은 예외 발생 — 여러 케이스 검증")
    @ParameterizedTest
    @ValueSource(ints = {8500, 1250, 1999})
    void 금액이_1000원_단위가_아니면_예외(int input) {
        assertThatThrownBy(() -> InputView.validateAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000원 단위");
    }
}
