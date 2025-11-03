package lotto;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public final class OutputView {
    private static final NumberFormat NF = NumberFormat.getInstance(Locale.KOREA);

    public static void printPurchased(int count) {
        System.out.println();
        System.out.printf("%d개를 구매했습니다.%n", count);
    }

    public static void printTickets(List<Lotto> tickets) {
        for (Lotto t : tickets) {
            System.out.println(t);
        }
    }

    public static void printStatisticsHeader() {
        System.out.println("\n당첨 통계");
        System.out.println("---");
    }

    public static void printRankLine(Rank rank, int cnt) {
        if (rank == Rank.NONE) {
            return;
        }
        System.out.printf("%s - %d개%n", rank.display(), cnt);
    }

    public static void printYield(double yieldPercent) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", yieldPercent);
    }

    public static void printError(String message) {
        System.err.println(message);
    }
}
