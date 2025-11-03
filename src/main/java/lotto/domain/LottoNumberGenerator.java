package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumberGenerator {
    public Lotto generate() {
        List<Integer> randomWinNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                .stream()
                .sorted() // 오름차순 정렬
                .toList(); // stream()은 데이터 흐름을 나타내는 중간 처리 도구라서 리스트가 아님 -> 리스트화 필요
        return new Lotto(randomWinNumbers); // 유효한 로또 객체로 전환
    }
}
