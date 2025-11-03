package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Money money = retryUntil(() -> Money.of(InputView.readPurchaseAmount()));

        List<Lotto> tickets = issueTickets(money);
        printTickets(tickets);

        List<Integer> winningNumbers = retryUntil(InputView::readWinningNumbers);
        int bonus = retryUntil(() -> InputView.readBonusNumber(new HashSet<>(winningNumbers)));

        Winning winning = new Winning(winningNumbers, bonus);
        Result result = Result.of(tickets, winning);

        printStatistics(result, money.amount());
    }

    private static List<Lotto> issueTickets(Money money) {
        LottoGenerator generator = new LottoGenerator();
        int count = money.toTicketCount();
        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tickets.add(generator.createRandomTicket());
        }
        return tickets;
    }

    private static void printTickets(List<Lotto> tickets) {
        OutputView.printPurchased(tickets.size());
        OutputView.printTickets(tickets);
    }

    private static void printStatistics(Result result, long purchaseAmount) {
        OutputView.printStatisticsHeader();
        OutputView.printRankLine(Rank.FIFTH,  result.counts().get(Rank.FIFTH));
        OutputView.printRankLine(Rank.FOURTH, result.counts().get(Rank.FOURTH));
        OutputView.printRankLine(Rank.THIRD,  result.counts().get(Rank.THIRD));
        OutputView.printRankLine(Rank.SECOND, result.counts().get(Rank.SECOND));
        OutputView.printRankLine(Rank.FIRST,  result.counts().get(Rank.FIRST));
        double yieldPercent = calcYieldPercent(result.totalPrize(), purchaseAmount);
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
    private interface SupplierWithException<T> { T get(); }
}
