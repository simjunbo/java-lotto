package lotto.domain;

import lotto.service.LottoVendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LottoWinningStatisticsTest {

    private Lottos lottos;

    @BeforeEach
    void setUp() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6))
        );
        Lotto lotto2 = new Lotto(Arrays.asList(
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6),
                LottoNumber.of(7),
                LottoNumber.of(8),
                LottoNumber.of(9))
        );
        this.lottos = new Lottos(Arrays.asList(lotto,lotto2));
    }

    @ParameterizedTest
    @ValueSource(strings = "1,2,3,4,5,6")
    @DisplayName("로또 6개일치 3개일치 건수 검증")
    void lotto_winningStatistics_matchCount(String winningNumber) {
        //given
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(winningNumber);

        //when
        List<LottoResult> lottoResults = LottoVendingMachine.lottoWinningResults(lottos,lottoWinningNumber,7);
        LottoWinningResults winningResults = LottoWinningStatistics.getStatistics(lottoResults);
        List<LottoWinningResult> winningResultList = winningResults.getLottoWinningResults();

        int first = winningResultList.stream()
                .filter(e -> e.getLottoResult() == LottoResult.FIRST)
                .findFirst()
                .get()
                .getCount();
        int fifth = winningResultList.stream()
                .filter(e -> e.getLottoResult() == LottoResult.FIFTH)
                .findFirst()
                .get()
                .getCount();

        //then
        assertAll(
                () -> assertThat(first).isEqualTo(1),
                () -> assertThat(fifth).isEqualTo(1)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = "1,2,3,4,5,6")
    @DisplayName("로또 상금금액 일치 확인")
    void lotto_winningStatistics_profit(String winningNumber) {
        //given
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(winningNumber);

        //when
        List<LottoResult> lottoResults = LottoVendingMachine.lottoWinningResults(lottos,lottoWinningNumber,7);
        LottoWinningResults winningResults = LottoWinningStatistics.getStatistics(lottoResults);
        BigInteger profit =  LottoWinningStatistics.getProfit(winningResults);

        //then
        assertThat(profit.intValue()).isEqualTo(2000005000);
    }

    @ParameterizedTest
    @ValueSource(strings = "1,2,3,4,5,7")
    @DisplayName("로또 2등 당첨금 확인")
    void lotto_secondResult_profit(String winningNumber) {
        //given
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(winningNumber);

        //when
        List<LottoResult> lottoResults = LottoVendingMachine.lottoWinningResults(lottos,lottoWinningNumber,6);
        List<LottoResult> secondResult = Collections.singletonList(lottoResults.get(0));
        LottoWinningResults winningResults = LottoWinningStatistics.getStatistics(secondResult);
        BigInteger profit =  LottoWinningStatistics.getProfit(winningResults);

        //then
        assertThat(profit.intValue()).isEqualTo(30000000);
    }
}