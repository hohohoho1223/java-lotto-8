package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @DisplayName("당첨 번호가 6개가 아닌 경우 예외 발생 — 여러 케이스 검증")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void 당첨_번호가_6개가_아니면_예외(String input) {
        List<Integer> lottoNumbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(InputView::parseToInt)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> InputView.validateLottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개");
    }

    @Test
    void 당첨_번호에_중복이_있으면_예외() {
        List<Integer> lottoNumbers = List.of(1, 1, 3, 4, 5, 6);
        assertThatThrownBy(() -> InputView.validateLottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

    @DisplayName("당첨 번호가 1~45 범위를 벗어나면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"0,2,3,4,5,6", "1,2,3,4,5,46", "0,1,2,3,4,100"})
    void 당첨_번호가_범위를_벗어나면_예외(String input) {
        List<Integer> lottoNumbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(InputView::parseToInt)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> InputView.validateLottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45");
    }

    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외 발생")
    @Test
    void 보너스_번호_범위_예외() {
        //기존에 입력한 로또 번호
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 46;

        assertThatThrownBy(() -> InputView.validateBonusNumber(lottoNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45");
    }


    @DisplayName("보너스 번호가 당첨 번호와 중복될 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 6})
    void 보너스_번호에_당첨_번호와_중복이_있으면_예외(int bonusNumber) {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> InputView.validateBonusNumber(lottoNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");

    }
}

