package lotto;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        List<Lotto> tickets = new ArrayList<>();
        LottoGenerator generator = new LottoGenerator();

        Money money = retryUntil(() -> Money.of(InputView.readPurchaseAmount()));
        int count = money.toTicketCount();

        for (int i = 0; i < count; i++) {
            tickets.add(generator.createRandomTicket());
        }

        OutputView.printPurchased(count);
        OutputView.printTickets(tickets);

        List<Integer> winningNumbers = retryUntil(InputView::readWinningNumbers);

        Set<Integer> winningSet = new HashSet<>(winningNumbers);
        int bonus = retryUntil(() -> InputView.readBonusNumber(winningSet));
        Winning winning = new Winning(winningNumbers, bonus);
        Result result = Result.of(tickets, winning);

        OutputView.printStatisticsHeader();
        OutputView.printRankLine(Rank.FIFTH, result.counts().get(Rank.FIFTH));
        OutputView.printRankLine(Rank.FOURTH, result.counts().get(Rank.FOURTH));
        OutputView.printRankLine(Rank.THIRD, result.counts().get(Rank.THIRD));
        OutputView.printRankLine(Rank.SECOND, result.counts().get(Rank.SECOND));
        OutputView.printRankLine(Rank.FIRST, result.counts().get(Rank.FIRST));

        double yieldPercent = calcYieldPercent(result.totalPrize(), money.amount());
        OutputView.printYield(yieldPercent);
    }

    private static double calcYieldPercent(long totalPrize, long purchaseAmount) {
        double raw = (double) totalPrize * 100.0 / purchaseAmount;
        return Math.round(raw * 10.0) / 10.0;
    }

    private static <T> T retryUntil(SupplierWithException<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    @FunctionalInterface
    private interface SupplierWithException<T> {
        T get();
    }
}
