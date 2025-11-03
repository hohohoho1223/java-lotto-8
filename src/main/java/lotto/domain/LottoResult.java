package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> result = new HashMap<>();
    private final double profitRate;

    public LottoResult(List<Lotto> lottos, List<Integer> lottoNumbers, int bonusNumber) {
        for (Lotto lotto : lottos) {
            int matchCount = (int) lotto.getNumbers().stream()
                    .filter(lottoNumbers::contains)
                    .count();
            boolean matchBonus = lotto.getNumbers().contains(bonusNumber);
            LottoRank rank = LottoRank.valueOf(matchCount, matchBonus);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        profitRate = calculateProfit(lottos.size());
    }

    private double calculateProfit(int totalLottoCount) {
        int totalPrize = result.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
        return (double) totalPrize / (totalLottoCount * 1000) * 100;
    }

    public Map<LottoRank, Integer> getResult() {
        return result;
    }

    public double getProfitRate() {
        return profitRate;
    }
}