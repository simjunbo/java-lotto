package nextstep.step2.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoNumberTest {

	@Test
	@DisplayName("로또번호는 숫자여야 한다")
	public void inputWrongLastLottoNumberTest() {
		Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> LottoNumber.getValidateNumber("abc"))
				.withMessage("로또번호는 모두 숫자여야 합니다.");
	}

	@Test
	@DisplayName("로또번호는 1~45 사이의 숫자여야 한다.")
	public void lastLottoWrongNumberTest() {
		Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> LottoNumber.of(100))
				.withMessage("로또는 1~45의 숫자여야 합니다.");
	}

	@Test
	@DisplayName("객체비교 테스트 ")
	public void lottoNumberEqualsTest() {
		LottoNumber number1 = LottoNumber.of(1);
		LottoNumber number2 = LottoNumber.of(1);
		assertTrue(number1.equals(number2)); //true
		assertTrue(number1 == number2); // true
	}
}