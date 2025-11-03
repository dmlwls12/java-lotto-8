package lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class Result {
    private final Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
    private long totalPrize = 0;

    public Result() {
        for (Rank r : Rank.values()) {
            counts.put(r, 0);
        }
    }

    public void add(Rank rank) {
        counts.put(rank, counts.get(rank) + 1);
        totalPrize += rank.prize();
    }

    public Map<Rank, Integer> counts() {
        return counts;
    }

    public long totalPrize() {
        return totalPrize;
    }

    public static Result of(List<Lotto> tickets, Winning winning) {
        Result r = new Result();
        for (Lotto ticket : tickets) {
            Rank rank = winning.judge(ticket);
            r.add(rank);
        }
        return r;
    }
}
