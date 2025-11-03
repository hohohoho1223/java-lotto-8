package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    public static int inputPurchaseAmount() {
        while (true) {
            try {
                System.out.println("구입 금액을 입력해 주세요.");
                String input = Console.readLine();
                int amount = parseToInt(input);
                validateAmount(amount);
                return amount;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // [ERROR] 메시지 출력
            }
        }
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
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String input = Console.readLine();
                List<Integer> lottoNumbers = Arrays.stream(input.split(","))
                        .map(String::trim)
                        .map(InputView::parseToInt)
                        .collect(Collectors.toList());
                validateLottoNumbers(lottoNumbers);
                return lottoNumbers;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
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

    public static int inputBonusNumber(List<Integer> lottoNumbers) {
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요!");
                int bonusNumber = parseToInt(Console.readLine());
                validateBonusNumber(lottoNumbers, bonusNumber);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void validateBonusNumber(List<Integer> lottoNumbers, int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다!");
        }
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다!");
        }
    }
}
