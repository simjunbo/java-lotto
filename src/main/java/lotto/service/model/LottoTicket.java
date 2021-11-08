package lotto.service.model;

import lotto.service.value.LottoNumber;
import lotto.utils.Preconditions;

import java.util.List;

public class LottoTicket {
    private static final Integer MINIMUM_SIZE = 1;
    private static final Integer MAXIMUM_SIZE = 6;

    private final List<LottoNumber> numbers;

    private LottoTicket(List<LottoNumber> numbers) {
        Preconditions.checkNotNull(numbers, "numbers는 필수값 입니다.");
        Preconditions.checkMinimumNumber(numbers.size(), MINIMUM_SIZE,
                                         String.format("LottoNumber 갯수는 %s 이상이어야 합니다.", MINIMUM_SIZE));
        Preconditions.checkMaximumNumber(numbers.size(), MAXIMUM_SIZE,
                                         String.format("LottoNumber 갯수는 %s 이하이어야 합니다.", MAXIMUM_SIZE));

        this.numbers = numbers;
    }

    public static LottoTicket from(List<LottoNumber> numbers) {
        return new LottoTicket(numbers);
    }

    @Override
    public String toString() {
        return String.join(", ", numbers.toString());
    }
}
