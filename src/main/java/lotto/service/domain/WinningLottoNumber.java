package lotto.service.domain;

import lotto.service.value.LottoNumber;
import lotto.utils.Preconditions;

import java.util.List;

public class WinningLottoNumber {
    private final List<LottoNumber> winningLottoNumbers;
    private final LottoNumber bonusNumber;

    private WinningLottoNumber(List<LottoNumber> winningLottoNumbers, LottoNumber bonusNumber) {
        Preconditions.checkNotNull(winningLottoNumbers, "winningLottoNumbers는 필수값 입니다.");
        Preconditions.checkNotNull(bonusNumber, "bonusNumber 필수값 입니다.");

        this.winningLottoNumbers = winningLottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLottoNumber of(List<LottoNumber> numbers, LottoNumber bonusNumber) {
        return new WinningLottoNumber(numbers, bonusNumber);
    }

    public Integer getMatchingCount(LottoTicket lottoTicket) {
        return (int) winningLottoNumbers.stream()
                .filter(lottoTicket::isContains)
                .count();
    }

    public boolean isBonusNumberMatch(LottoTicket myLottoTicket) {
        return myLottoTicket.isContains(bonusNumber);
    }
}
