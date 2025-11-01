package lotto.view;

import camp.nextstep.edu.missionutils.Console;

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
}
