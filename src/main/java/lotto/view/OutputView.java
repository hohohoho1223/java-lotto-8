package lotto.view;

import lotto.domain.LottoRank;
import lotto.domain.LottoResult;

public class OutputView {
    public static void printResult(LottoResult result) {
        System.out.println("당첨 통계");
        System.out.println("---");

        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NONE) continue;
            int count = result.getResult().getOrDefault(rank, 0);
            if (rank == LottoRank.SECOND) {
                System.out.printf("5개 일치, 보너스 볼 일치 (%s원) - %d개%n",
                        String.format("%,d", rank.getPrize()), count);
                continue;
            }
            System.out.printf("%d개 일치 (%s원) - %d개%n",
                    rank.getMatchCount(),
                    String.format("%,d", rank.getPrize()),
                    count);
        }

        System.out.printf("총 수익률은 %.1f%%입니다.%n", result.getProfitRate());
    }
}