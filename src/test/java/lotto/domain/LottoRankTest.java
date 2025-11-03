package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoRankTest {

    @DisplayName("로또_등수에_따른_상금을_반환_해야한다.")
    @Test
    void 로또_등수에_따른_상금_검증() {
        assertThat(LottoRank.FIRST.getPrize()).isEqualTo(2_000_000_000);
        assertThat(LottoRank.SECOND.getPrize()).isEqualTo(30_000_000);
        assertThat(LottoRank.THIRD.getPrize()).isEqualTo(1_500_000);
        assertThat(LottoRank.FOURTH.getPrize()).isEqualTo(50_000);
        assertThat(LottoRank.FIFTH.getPrize()).isEqualTo(5_000);
    }

    @DisplayName("사용자의_입력번호와_당첨번호_간에_일치개수와_보너스_여부로_등수를_반환해야_한다.")
    @Test
    void 일치개수와_보너스_여부로_등수_검증() {
        assertThat(LottoRank.valueOf(6,false)).isEqualTo(LottoRank.FIRST);
        assertThat(LottoRank.valueOf(5,true)).isEqualTo(LottoRank.SECOND);
        assertThat(LottoRank.valueOf(5,false)).isEqualTo(LottoRank.THIRD);
        assertThat(LottoRank.valueOf(4,false)).isEqualTo(LottoRank.FOURTH);
        assertThat(LottoRank.valueOf(3,false)).isEqualTo(LottoRank.FIFTH);
        assertThat(LottoRank.valueOf(2,false)).isEqualTo(LottoRank.NONE);
    }
}


