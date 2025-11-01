package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    public static int inputPurchaseAmount() {
        System.out.println("구입 금액을 입력해주세요!:");
        String input = Console.readLine();
        int amount = parseToInt(input); // 중복된 코드(예외처리)는 메서드로 추출하기 위함
        validateAmount(amount);
        return amount;
    }

    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        }  catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력 해주세요~!");
        }
    }

    public static void validateAmount(int amount) {
        if (amount <1000) {
            throw new IllegalArgumentException("[ERROR] 입력금액은 1000원 이상이어야 합니다!");
        }
        if (amount%1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 입력금액은 1000원 단위여야 합니다!");
        }
    }

    public static List<Integer> inputLottoNumber() {
        System.out.println("예상되는 당첨 번호를 입력해주세요! (단, 쉼표(,)를 이용해 구분하여 입력하세요!:");
        String input = Console.readLine();
        List<Integer> lottoNumbers = Arrays.stream(input.split(","))
                .map(s -> s.trim())
                .map(InputView::parseToInt)
                .collect(Collectors.toList());
        validateLottoNumbers(lottoNumbers);
        return lottoNumbers;
    }

    public static void validateLottoNumbers(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다!");
        }
        if (lottoNumbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("[ERROR] 중복되지 않은 번호여야 합니다!");
        }
        if (lottoNumbers.stream().anyMatch(n -> n < 1 || n > 45)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
        }
    }
}
