package lotto;


import java.util.ArrayList;
import java.util.List;

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
