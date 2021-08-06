package lotto.view;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Prize;
import lotto.model.WinPrizes;
import lotto.utils.LottoCountCalculator;
import lotto.utils.PrizeRate;

public class LottoOutputView {

	private static final String BUY_RESULT_MESSAGE = "개를 구매했습니다.";
	private static final String RESULT_START_MESSAGE = "당첨 통계";
	private static final String DASH_LINE = "---------";
	public static final int ZERO_POINT = 0;

	public static void printLottoCount(int lottoCount) {
		System.out.println(lottoCount + BUY_RESULT_MESSAGE);
	}

	public static void printLottoList(Lottos lottos) {
		for (Lotto lotto : lottos.getLottos()) {
			printLottoGameView(lotto);
		}
		System.out.print("\n");
	}

	private static void printLottoGameView(Lotto lotto) {
		System.out.println(lotto.getLotto());
	}

	public static void displayLottoPrize(WinPrizes winPrizes, int lottoCount) {
		int money = LottoCountCalculator.calculateLottoMoney(lottoCount);
		System.out.println(RESULT_START_MESSAGE);
		System.out.println(DASH_LINE);
		int totalWinningMoney = winPrizes.getTotalWinningMoney();
		for (Prize prize : winPrizes.drawResultWinPrizes()) {
			printResultStatus(winPrizes, prize);
		}
		System.out.println("총 수익률은 " + PrizeRate.getPrizeEarningRate(totalWinningMoney, money) + "입니다.");
	}

	private static void printResultStatus(WinPrizes winPrizes, Prize prize) {
		if (prize.getWinningMoney() > ZERO_POINT) {
			System.out.println(prize.getCountOfMatch() +
				"개 일치" +
				"(" + prize.getWinningMoney() + ")" +
				"-" + winPrizes.findWinPrizeGrade(prize) + "개");
		}
	}

}
